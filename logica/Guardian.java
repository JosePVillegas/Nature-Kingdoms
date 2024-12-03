package logica;

import java.io.Serializable;

public class Guardian implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;
    public int gx;
    public int gy;
    public int valor;
    public String tipo;
    public boolean estaActivo;
    private Jugador jugador;
    private Escenario escenario;

    private float velocidad_horizontal = 4f;
    private float velocidad_vertical = 0;
    private final float gravedad = 0.8f;
    private final float impulsoSalto = -12;
    private boolean estaEnSuelo = false;
    private int anchura = 50;
    private int altura = 50;
    private int direccion = 1;
    private int rangoDeteccion = 300;

    public Guardian(int gx, int gy, int valor, String tipo, boolean estaActivo, Jugador jugador, Escenario escenario) {
        this.gx = gx;
        this.gy = gy;
        //this.valor = valor;
        //this.tipo = tipo;
        this.estaActivo = estaActivo;
        this.jugador = jugador;
        this.escenario = escenario;

        System.out.println("Guardian creado en posición: (" + gx + ", " + gy + ")");
    }

    public void mover() {
        if (!estaActivo) return;

        try {
            System.out.println("Moviendo Guardian. Posición actual: (" + gx + ", " + gy + ")");
            float distanciaJugador = Math.abs(jugador.getPosicionX() - gx);

            if (distanciaJugador <= rangoDeteccion) {
                direccion = (jugador.getPosicionX() > gx) ? 1 : -1;
                //gx += velocidad_horizontal * direccion;

                if (gx < 0) gx = 0;
                if (gx > escenario.MAX_WORLD_WIDTH - anchura) gx = escenario.MAX_WORLD_WIDTH - anchura;

                if (estaEnSuelo && hayObstaculoEnFrente()) {
                    saltar();
                }
            }

            velocidad_vertical += gravedad;
            gy += velocidad_vertical;
            manejarColisionesObstaculos();

            System.out.println("Nueva posición del Guardian: (" + gx + ", " + gy + ")");
        } catch (Exception e) {
            System.err.println("Error en mover de Guardian: " + e.getMessage());
        }
    }

    private boolean hayObstaculoEnFrente() {
        int checkX = direccion > 0 ? gx + anchura : gx - 10;
        for (Obstaculo ob : escenario.listaObstaculo) {
            if (ob.estaActivo && 
                checkX >= ob.x && checkX <= ob.x + ob.valor &&
                gy + altura >= ob.y && gy < ob.y + ob.valor) {
                return true;
            }
        }
        return false;
    }

    private void manejarColisionesObstaculos() {
        for (Obstaculo ob : escenario.listaObstaculo) {
            if (ob.estaActivo) {
                if (gx + anchura > ob.x && gx < ob.x + ob.valor) {
                    if (direccion > 0) gx = ob.x - anchura;
                    else if (direccion < ob.x + ob.valor) gx = ob.x + ob.valor;
                }

                if (gx + anchura > ob.x && gx < ob.x + ob.valor &&
                    gy + altura >= ob.y && gy + altura <= ob.y + 50 &&
                    velocidad_vertical >= 0) {
                    gy = ob.y - altura;
                    velocidad_vertical = 0;
                    estaEnSuelo = true;
                }
            }
        }
    }

    private void saltar() {
        if (estaEnSuelo) {
            velocidad_vertical = impulsoSalto;
            estaEnSuelo = false;
        }
    }

    public boolean verificarColision(Jugador jugador) {
        return (jugador.getPosicionX() + 30 > gx &&
                jugador.getPosicionX() < gx + anchura &&
                jugador.getPosicionY() + 30 > gy &&
                jugador.getPosicionY() < gy + altura);
    }

    @Override
    public void run() {
        while (estaActivo) {
            try {
                mover();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.err.println("Error en el hilo del Guardian: " + e.getMessage());
            }
        }
    }

	public float getVelocidad_horizontal() {
		return velocidad_horizontal;
	}

	public void setVelocidad_horizontal(float velocidad_horizontal) {
		this.velocidad_horizontal = velocidad_horizontal;
	}
}
