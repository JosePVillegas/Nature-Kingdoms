package logica;

import java.io.Serializable;

/** 
<p> Descripcion: Esta clase gestiona las propiedades y 
comportamientos de una gema, implementando la interfaz
 Runnable para potential procesamiento en un hilo. </p>
**/
public class Gema implements Runnable, Serializable {
	
	/**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización. </p>
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 Constructor para iniciar la clase Gema.
	 
	 @param gemx (Coordenada X de la gema.)
     @param gemy )Coordenada Y de la gema.)
     @param valor (Valor de la gema.)
     @param tipo (Tipo de gema.)
     @param estaActivo (Estado inicial de actividad de
      la gema.)
	 **/
	public Gema(int gemx, int gemy, int valor, String tipo
	, boolean estaActivo) {
		
		super();
		this.gemx = gemx;
		this.gemy = gemy;
		this.valor = valor;
		this.tipo = tipo;
		this.estaActivo = true;
		
	}
	
	/**
	 int "gemx" albergara las coordendas en el eje X.
	 **/
	public int gemx;
	
	/**
	 int "gemy" albergara las coordenadas en el eje y.
	 **/
	public int gemy;
	
	/**
	 int "valor" valor de la gema.
	 **/
	public int valor;
	
	/**
	 String "tipo" albergara el tipo de gema.
	 **/
	public String tipo;
	
	/**
	 boolean "estaActivo" Indica si la gema está activa en
	  el juego.
	 **/
	public boolean estaActivo;
	
	/**
	  @param run
	  
	  <p> 
	  El metodo run implementa un breve pausado del
	  hilo de la gema.
      Maneja la excepción InterruptedException en caso de
      interrupción del hilo. </p>
	 **/
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		
}
	
	/**
	  @param verificarColision
	  
	  <p> El metodo verificarColision verifica si hay colisión
	  entre la gema y el jugador. </p>
	  
	  @param jugador Jugador
	 **/
	
	public boolean verificarColision(Jugador jugador) {
		
	    return (jugador.getPosicionX() >= gemx && 
	            jugador.getPosicionX() <= gemx + valor &&
	            jugador.getPosicionY() >= gemy && 
	            jugador.getPosicionY() <= gemy + valor);
	    
	}
	
}