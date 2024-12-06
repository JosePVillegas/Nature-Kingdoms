package logica;

import java.io.Serializable;

/** 
<p> Descripcion: Esta clase define un guardián dentro del juego, 
quien deberia interactuar con el jugador. 
</p>
**/
public class Guardian implements Runnable, Serializable {
	
	/**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización. </p>
	 **/
	private static final long serialVersionUID = 1L;
	
	/**
	 Constructor para iniciar un guardián en el juego.
	 @param gx (Posición inicial en el eje X del guardián)
	 @param gy (Posición inicial en el eje Y del guardián)
	 @param valor (Valor o puntos asociados al guardián, generalmente se utiliza para su poder o dificultad)
	 @param tipo (Tipo de guardián, define su comportamiento o características especiales)
	 @param estaActivo (Indica si el guardián está activo y puede interactuar con el jugador)
	 @param jugador (Referencia al jugador, utilizado para interacciones con el guardián)
	 @param escenario (Referencia al escenario donde el guardián interactúa)
	 @param velocidad_horizontal (Velocidad de movimiento horizontal del guardián)
	 @param velocidad_vertical (Velocidad de movimiento vertical del guardián)
	 @param estaEnSuelo (Indica si el guardián está en el suelo o flotando)
	 @param anchura (Ancho del guardián, utilizado para representar su tamaño)
	 @param altura (Altura del guardián, utilizado para representar su tamaño)
	 @param direccion (Dirección en la que se mueve el guardián, puede ser hacia la izquierda o derecha)
	 @param rangoDeteccion (Distancia máxima a la que el guardián puede detectar al jugador u otros objetos)
	 **/
	public Guardian(int gx, int gy, int valor, String tipo, boolean estaActivo, Jugador jugador, Escenario escenario,
			float velocidad_horizontal, float velocidad_vertical, boolean estaEnSuelo, int anchura, int altura,
			int direccion, int rangoDeteccion) {
		
		super();
		this.gx = gx;
		this.gy = gy;
		this.valor = valor;
		this.estaActivo = estaActivo;
		this.jugador = jugador;
		this.escenario = escenario;
		this.velocidad_horizontal = velocidad_horizontal;
		this.velocidad_vertical = velocidad_vertical;
		this.direccion = direccion;
		this.rangoDeteccion = rangoDeteccion;
		
	}

	/**
	 int "gx" albergara las coordendas en el eje X.
	 **/
	public int gx;
	
	/**
	 int "gy" albergara las coordendas en el eje Y.
	 **/
    public int gy;
    
    /**
	 int "valor" albergara el valor asociado del guardian.
	 **/
    public int valor;
    
    /**
	 boolean "estaActivo" verifica si el guardian esta activo.
	 **/
    public boolean estaActivo;
    
    /**
	 Jugador "Jugador" hace referencia la interaccion entre la clase Jugador y la Clase Guardian.
	 **/
    private Jugador jugador;
    
    /**
	 Escenario "escenario" hace referencia la interaccion entre la clase Escenario y la Clase Guardian.
	 **/
    private Escenario escenario;

         //Valores Constantes //
    
    private float velocidad_horizontal = 4f;
    private float velocidad_vertical = 0;
    private final float gravedad = 0.8f;
    private int direccion = 1;
    private int rangoDeteccion = 300;

    /**
     @param mover
     
     <p> Método que maneja el movimiento del guardián en el escenario. <\p>
     **/
    public void mover() {
    	
        if (!estaActivo) return;

        try {
        	
            System.out.println("Moviendo Guardian. Posición actual: (" + gx + ", " + gy + ")");
            float distanciaJugador = Math.abs(jugador.getPosicionX() - gx);

            if (distanciaJugador <= rangoDeteccion) {
            	
                direccion = (jugador.getPosicionX() > gx) ? 1 : -1;
                gx += velocidad_horizontal * direccion;

            }

            velocidad_vertical += gravedad;
            gy += velocidad_vertical;
            manejarColisionesObstaculos();

            System.out.println("Nueva posición del Guardian: (" + gx + ", " + gy + ")");
            
        } catch (Exception e) {
        	
            System.err.println("Error en mover de Guardian: " + e.getMessage());
            
        }
        
    }
    
    /**
    @param manejarColisionesObstaculos
    
    El método manejarColisionesObstaculos verifica y maneja las colisiones entre el guardián y los obstáculos en el escenario. </p>
    **/
    
    private void manejarColisionesObstaculos() {
    	
        for (Obstaculo ob : escenario.listaObstaculo) {
        	
            if (ob.estaActivo) {
                
            }
            
        }
        
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
    
    // Getters and Setters//

	public float getVelocidad_horizontal() {
		
		return velocidad_horizontal;
		
	}

	public void setVelocidad_horizontal(float velocidad_horizontal) {
		
		this.velocidad_horizontal = velocidad_horizontal;
		
	}
	
}