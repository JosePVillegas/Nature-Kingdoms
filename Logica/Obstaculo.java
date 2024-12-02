package logica;

/** 
<p> Descripcion: Esta clase representa un obstáculo en un 
juego que puede detectar colisiones con un jugador.
Implementa Runnable para potencial ejecución en un hilo 
separado.
</p>
**/

public class Obstaculo implements Runnable {
	
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
	 int "x" Coordenada X del obstáculo.
	 **/
	
	public int x;
	
	/**
	 int "y" Coordenada Y del obstáculo.
	 **/
	
	public int y;
	
	/**
	 int "valor" Tamaño o dimensión del obstáculo.
	 **/
	
	public int valor;
	
	/**
	 String "tipo" Tipo o categoría del obstáculo.
	 **/
	
	public String tipo;
	
	/**
	 bollean "estaActivo" Indica si el obstáculo está activo.
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