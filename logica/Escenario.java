package logica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Escenario representa el entorno del juego, incluyendo elementos como el jugador, 
 * obstáculos, guardianes y gemas. También gestiona la lógica de cámara y las colisiones.
 * 
 */
public class Escenario implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Ancho del escenario. */
    public int ancho;

    /** Alto del escenario. */
    public int alto;

    /** Instancia del jugador dentro del escenario. */
    public Jugador jugador;

    /** Lista de obstáculos en el escenario. */
    public ArrayList<Obstaculo> listaObstaculo;

    /** Lista de guardianes en el escenario. */
    public ArrayList<Guardian> listaGuardian;

    /** Lista de gemas en el escenario. */
    public ArrayList<Gema> listaGema;

    /** Desplazamiento horizontal del nivel. */
    public int xLvlOffset = 0;

    /** Límite izquierdo de la cámara. */
    public final int LEFT_BORDER = 100;

    /** Límite derecho de la cámara. */
    public final int RIGHT_BORDER = 400;

    /** Ancho máximo del mundo del juego. */
    public final int MAX_WORLD_WIDTH = 2000;

    /**
     * Constructor por defecto que inicializa el escenario con un jugador, obstáculos, 
     * guardianes y gemas predeterminadas.
     */
    public Escenario() {
        ancho = 854;
        alto = 500;

        this.jugador = new Jugador("P1", 1, 10, 400, 420, false, null);
        this.jugador.color = "blue";

        this.listaObstaculo = new ArrayList<>();
        this.listaGuardian = new ArrayList<>();
        this.listaGema = new ArrayList<>();

        // Agregar elementos al escenario
        inicializarElementos();
    }

    /**
     * Método para inicializar los elementos del escenario como obstáculos, gemas y guardianes.
     */
    private void inicializarElementos() {
        // Suelo principal
        Obstaculo suelo = new Obstaculo(0, 450, MAX_WORLD_WIDTH, "suelo", true);
        listaObstaculo.add(suelo);

     // Inicializa las plataformas dispersas
        listaObstaculo.add(new Obstaculo(200, 300, 100, "plataforma", true)); // Plataforma en una posición más cercana
        listaObstaculo.add(new Obstaculo(400, 200, 100, "plataforma", true)); // Plataforma más cerca que antes
        listaObstaculo.add(new Obstaculo(600, 150, 100, "plataforma", true)); // Otra plataforma más cerca
        listaObstaculo.add(new Obstaculo(800, 250, 100, "plataforma", true)); // Plataforma más cercana
        listaObstaculo.add(new Obstaculo(1000, 200, 100, "plataforma", true)); // Última plataforma más cerca
        listaObstaculo.add(new Obstaculo(1200, 250, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1400, 180, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1600, 300, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1800, 220, 100, "plataforma", true));

        // Reposicionar la gema al final del lienzo
        listaGema.clear(); // Eliminar cualquier gema anterior
        listaGema.add(new Gema(MAX_WORLD_WIDTH - 100, 200, 100, "nivel", true));

        // Guardianes
        Guardian guardian1 = new Guardian(150, 400, 50, "patrulla", true, jugador, this);
        Guardian guardian2 = new Guardian(350, 400, 50, "patrulla", true, jugador, this);
        
        
        
        listaGuardian.add(guardian1);
        listaGuardian.add(guardian2);
        
        int intervaloGuardianes = 200; // Distancia entre guardianes
        for (int x = 100; x < MAX_WORLD_WIDTH; x += intervaloGuardianes) {
            Guardian guardian = new Guardian(x, 400, 50, "patrulla", true, jugador, this);
            listaGuardian.add(guardian);
            new Thread(guardian).start(); // Inicia el movimiento de cada guardián
        }
        /*for (Guardian guardian : listaGuardian) {
            new Thread(guardian).start();
        }*/
    }


    /**
     * Actualiza el desplazamiento de la cámara en función de la posición del jugador.
     */
    public void updateCameraOffset() {
        int playerX = (int) jugador.getPosicionX();
        int diff = playerX - xLvlOffset;

        if (diff > RIGHT_BORDER)
            xLvlOffset += diff - RIGHT_BORDER;
        else if (diff < LEFT_BORDER)
            xLvlOffset += diff - LEFT_BORDER;

        xLvlOffset = Math.max(0, Math.min(xLvlOffset, MAX_WORLD_WIDTH - ancho));
    }

    /**
     * Verifica las colisiones entre el jugador y los elementos del escenario 
     * como obstáculos, guardianes y gemas.
     */
    public void verificarColisiones() {
        verificarColisionesObstaculos();
        verificarColisionesGuardianes();
        verificarColisionesGemas();
    }

    private void verificarColisionesObstaculos() {
        for (Obstaculo o : listaObstaculo) {
            if (o.verificarColision(jugador) && jugador.getPosicionY() + 50 > o.y) {
                jugador.setPosicionY(o.y - 50);
                jugador.setEstaEnSuelo(true);
            }
        }
    }

    private void verificarColisionesGuardianes() {
        for (Guardian g : listaGuardian) {
            if (g.verificarColision(jugador)) {
                jugador.setVida(jugador.getVida() - 1);

                if (jugador.getVida() <= 0) {
                    reiniciarJugador();
                } else {
                    jugador.setPosicionX(jugador.getPosicionX() + (g.gx > jugador.getPosicionX() ? -50 : 50));
                }
            }
        }
    }

    private void verificarColisionesGemas() {
        for (Gema gema : listaGema) {
            if (gema.verificarColision(jugador)) {
                gema.estaActivo = false;
                System.out.println("¡Nivel completado!");
            }
        }
    }

    private void reiniciarJugador() {
        jugador.setPosicionX(40);
        jugador.setPosicionY(420);
        jugador.setVida(1);
        System.out.println("¡Jugador eliminado por Guardian!");
    }
}
