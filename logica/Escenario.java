package logica;

import java.io.Serializable;
import java.util.ArrayList;

/**
 <p> Descripcion: Clase Escenario representa el entorno del juego, incluyendo elementos como el jugador, 
 obstáculos, guardianes y gemas. También gestiona la lógica de cámara y las colisiones. <\p>
 **/
public class Escenario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
	 int "ancho" albergara el ancho del escenario.
	 **/ 
    public int ancho;

    /**
	 int "alto" albergara el alto del escenario.
	 **/ 
    public int alto;

    /**
	 Jugador "jugador" albergara la instancia del Jugador dentro del escenario.
	 **/ 
    public Jugador jugador;

    /**
	 ArrayList<Obstaculo> "listaObstaculo" albergara una lista de generacion de suelo y plataformas.
	 **/ 
    public ArrayList<Obstaculo> listaObstaculo;

    /**
	 ArrayList<Guardian> "listaGuardian" albergara una lista de generacion de los guardianes (enemigos).
	 **/ 
    public ArrayList<Guardian> listaGuardian;

    /**
	 ArrayList<Gema> "listaGema" albergara una lista de generacion de la gema (Recompensa).
	 **/
    public ArrayList<Gema> listaGema; // No deberia existir

    /**
	 int "xLvlOffset" desplazamiento de la camara.
	 **/ 
    public int xLvlOffset = 0;

    /**
	 int "LEFT_BORDER" limite de borde izquierdo de la camara.
	 **/ 
    public final int LEFT_BORDER = 100;

    /**
	 int "RIGHT_BORDER" limite de borde derecho de la camara.
	 **/ 
    public final int RIGHT_BORDER = 400;

    /**
	 int "MAX_WORLD_WIDTH" composicion maxima de la camara.
	 **/ 
    public final int MAX_WORLD_WIDTH = 2000;

    /**
     Constructor por defecto que inicializa el escenario con un jugador, obstáculos, 
     guardianes y gemas predeterminadas.
     **/
    public Escenario() {
    	
        ancho = 854;
        alto = 500;

        this.jugador = new Jugador(1, 10, 400, 420, false);
        this.jugador.color = "blue";

        this.listaObstaculo = new ArrayList<>();
        this.listaGuardian = new ArrayList<>();
        this.listaGema = new ArrayList<>();

        // Agregar elementos al escenario
        inicializarElementos();
        
    }

    /**
	  @param inicializarElementos
	  
	  <p> El metodo inicializarElementos crea los objetos y los implemente en el escenario. </p>
	 **/
    private void inicializarElementos() {
    	
        // Suelo principal
        Obstaculo suelo = new Obstaculo(0, 450, MAX_WORLD_WIDTH, "suelo", true);
        listaObstaculo.add(suelo);

     // Plataformas dispersas
        listaObstaculo.add(new Obstaculo(200, 300, 100, "plataforma", true)); // Plataforma en una posición más cercana
        listaObstaculo.add(new Obstaculo(400, 200, 100, "plataforma", true)); // Plataforma más cerca que antes
        listaObstaculo.add(new Obstaculo(600, 150, 100, "plataforma", true)); // Otra plataforma más cerca
        listaObstaculo.add(new Obstaculo(800, 250, 100, "plataforma", true)); // Plataforma más cercana
        listaObstaculo.add(new Obstaculo(1000, 200, 100, "plataforma", true)); // Última plataforma más cerca
        listaObstaculo.add(new Obstaculo(1200, 250, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1400, 180, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1600, 300, 100, "plataforma", true));
        listaObstaculo.add(new Obstaculo(1800, 220, 100, "plataforma", true));

        // Gema
        listaGema.clear(); // Eliminar cualquier gema anterior
        listaGema.add(new Gema(MAX_WORLD_WIDTH - 100, 200, 100, "nivel", true));

        // Guardianes
        Guardian guardian1 = new Guardian(150, 400, 50, "patrulla", true, jugador, this, LEFT_BORDER, LEFT_BORDER, false, LEFT_BORDER, LEFT_BORDER, LEFT_BORDER, LEFT_BORDER);
        Guardian guardian2 = new Guardian(350, 400, 50, "patrulla", true, jugador, this, LEFT_BORDER, LEFT_BORDER, false, LEFT_BORDER, LEFT_BORDER, LEFT_BORDER, LEFT_BORDER);
        
        listaGuardian.add(guardian1);
        listaGuardian.add(guardian2);
        
        int intervaloGuardianes = 200; // Distancia entre guardianes
        for (int x = 100; x < MAX_WORLD_WIDTH; x += intervaloGuardianes) {
            Guardian guardian = new Guardian(x, 400, 50, "patrulla", true, jugador, this, x, x, false, x, x, x, x);
            listaGuardian.add(guardian);
            new Thread(guardian).start(); // Inicia el movimiento de cada guardián
            
        }

    }

    /**
	  @param updateCameraOffset
	  
	  <p> El metodo updateCameraOffset gestiona la dinamica de la camara en el escenario. </p>
	 **/
    public void updateCameraOffset() {
    	
        int playerX = (int) jugador.getPosicionX();
        int diff = playerX - xLvlOffset;

        if (diff > RIGHT_BORDER)
            xLvlOffset += diff - RIGHT_BORDER;
        else if (diff < LEFT_BORDER)
            xLvlOffset += diff - LEFT_BORDER;

        xLvlOffset = Math.max(0, Math.min(xLvlOffset, MAX_WORLD_WIDTH - ancho));
        
    }

}