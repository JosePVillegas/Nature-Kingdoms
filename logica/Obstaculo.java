package logica;

import java.io.Serializable;

/** 
<p> Descripcion: Esta clase representa un obstáculo en un 
juego que puede detectar colisiones con un jugador.
Implementa Runnable para potencial ejecución en un hilo 
separado. </p>
**/
public class Obstaculo implements Runnable, Serializable {
	
	/**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización. </p>
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 Constructor para iniciar los obstaculos.
	 @param x (Coordenada X del obstáculo).
     @param y (Coordenada Y del obstáculo).
     @param valor (Tamaño o dimensión del obstáculo).
     @param tipo T(ipo o categoría del obstáculo).
     @param estaActivo (Indica si el obstáculo está activo
     (se sobrescribe a true)).
	 **/
	public Obstaculo(int x, int y, int valor, String tipo,
	boolean estaActivo) {
		
		super();
		this.x = x;
		this.y = y;
		this.valor = valor;
		this.tipo = tipo;
		this.estaActivo = true;
		
	}
	
	/**
	 int "x" albergara las coordendas en el eje X.
	 **/
	public int x;
	
	/**
	 int "x" albergara las coordendas en el eje Y.
	 **/
	public int y;
	
	/**
	 int "valor" albergara el tamaño o dimension del obstaculo.
	 **/
	public int valor;
	
	/**
	 String "tipo" albergara el tipo de obstaculo.
	 **/
	public String tipo;
	
	/**
	 boolean "estaActivo" verificar si los obstaculo estan actvio.
	 **/
	public boolean estaActivo;
	
	/**
	  @param verificarColision
	  
	  <p> 
	  El metodo verificarColision verifica si hay una 
	  colisión entre el obstáculo y el jugador. </p>
	  
	  @param jugador Jugador
	 **/
	public boolean verificarColision(Jugador jugador) {
		
		return (jugador.getPosicionX() < x + valor &&
                jugador.getPosicionX() + 50 > x &&
                jugador.getPosicionY() + 50 > y &&
                jugador.getPosicionY() < y + valor);
		
	}

	@Override
	public void run() {}
	
}