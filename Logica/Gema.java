package logica;

/** 
<p> Descripcion: Esta clase gestiona las propiedades y 
comportamientos de una gema, implementando la interfaz
 Runnable para potential procesamiento en un hilo.</p>
**/

public class Gema implements Runnable {
	
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
	  El metodo mostrarDatos implementa un breve pausado del
	  hilo de la gema.
      Maneja la excepción InterruptedException en caso de
      interrupción del hilo.</p>
	 **/
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			Thread.sleep(50);
			
		} catch(InterruptedException e) {
			
			System.out.println("Error en la Gema: " 
			+ e.getMessage());
			
		}
		
	}
	
	/**
	  @param verificarColision
	  
	  <p> 
	  El metodo verificarColision verifica si hay colisión
	  entre la gema y el jugador. </p>
	  
	  @param jugador Jugador
	 **/
	
	public boolean verificarColision(Jugador jugador) {
		
	    return (jugador.getPosicionX() >= gemx && 
	            jugador.getPosicionX() <= gemx + valor &&
	            jugador.getPosicionY() >= gemy && 
	            jugador.getPosicionY() <= gemy + valor);
	    
	}
	
	/**
	  @param reiniciar
	  
	  <p> 
	  El metodo reiniciar Reinicia la posición y estado de
	   la gema. </p>
	  
	  @param nuevaX
	  @param nuevaY
	 **/
	
	public void reiniciar(int nuevaX, int nuevaY) {
		
		this.gemx = nuevaX;
		this.gemy = nuevaY;
		this.estaActivo = true;
		
	}
	
}