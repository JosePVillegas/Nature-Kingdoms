package logica;

import java.util.ArrayList;

/** 
 <p> Descripcion: Clase representa el escenario para un
  juego de plataformas 2D.</p>
**/

public class Escenario {
	
	/**
	 int "ancho" albergara el ancho del escenario.
	 **/
	
	public int ancho;
	
	/**
	 int "alto" albergara el alto del escenario.
	 **/
	
	public int alto;
	
	/**
	 Jugador "jugador" agrega la clase Jugador en
	 el escenario.
	 **/
	
	public Jugador jugador;
	
	/** 
	  <p> La listaObstaculo almacena instancias 
	  de la clase Obstaculo y
	   permite realizar operaciones como agregar el suelo
	   y las plataformas. </p>
	 **/
	
	public ArrayList <Obstaculo> listaObstaculo;
	
	/** 
	  <p> La listaGuardian almacena instancias 
	  de la clase Guardian y
	   permite realizar operaciones como listar varios
	   guardianes que apareceran a lo largo del juego. </p>
	 **/
	
	public ArrayList <Guardian> listaGuardian;
	
	/** 
	  <p> La listaGema no deberia existir. </p>
	 **/
	
	public ArrayList <Gema> listaGema;
	
	/**
	 Constructor para iniciar el escenario.
	 **/
	
	public Escenario() {
		
		ancho = 500;
		alto = 500;
		
		this.jugador = new Jugador("P1", 40, 10, 400, 420
		, false, null);
		
        this.jugador.color = "blue";
		
		this.listaObstaculo = new ArrayList <Obstaculo>();
		this.listaGuardian = new ArrayList <Guardian>();
		this.listaGema = new ArrayList <Gema>();
		
		Obstaculo suelo = new Obstaculo(0, 450, ancho
		, "suelo", true);
		
        listaObstaculo.add(suelo);
        
        Obstaculo plataforma1 = 
        new Obstaculo(100, 300, 100, "plataforma", true);
        
        Obstaculo plataforma2 = 
        new Obstaculo(250, 200, 100, "plataforma", true);
        
        Obstaculo plataforma3 =
        new Obstaculo(350, 350, 100, "plataforma", true);
        
        listaObstaculo.add(plataforma1);
        listaObstaculo.add(plataforma2);
        listaObstaculo.add(plataforma3);
        
        // Add Gema above platform 3
        Gema gema = new Gema(385, 310, 30, "nivel", true);
        listaGema.add(gema);
        
	}
	
	/**
    @param verificarColisiones
    <p> El metodo verificarColisiones verifica las colisiones
     entre los elementos del juego..</p>
    **/
	
	public void verificarColisiones() {
		
		for (int i = 0; i < listaObstaculo.size(); i++) {
			
			Obstaculo o = listaObstaculo.get(i);
			if(o.verificarColision(jugador)) {
				
				if(jugador.getPosicionY() + 50 > o.y) {
					
					jugador.setPosicionY(o.y - 50);
					jugador.setEstaEnSuelo(true);
					
				}
				
			}
			
		}
		
		for(int i = 0; i < listaGuardian.size(); i++) {
			
			Guardian g = listaGuardian.get(i);
			if(g.verificarColision(jugador)) {}
			
		}
		
		// Check for Gema collection
		for(int i = 0; i < listaGema.size(); i++) {
			
			Gema gema = listaGema.get(i);
			if(gema.verificarColision(jugador)) {
				
				// Level complete when gem is collected
				gema.estaActivo = false;
				System.out.println("¡Nivel completado!");
				
			}
			
		}
		
	}
		
}