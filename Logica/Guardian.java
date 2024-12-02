
package logica;

/** 
<p> Descripcion: Esta clase representa al enemigo del 
juego, que se mueve de manera autónoma y puede detectar 
 colisiones con un Jugador.
 Implementa Runnable para permitir su ejecución en un hilo
  separado.</p>
**/

public class Guardian implements Runnable {
	
	/**
	 Constructor para iniciar un guardian.
	  @param gx (oordenada X inicial del guardian)
      @param gy (Coordenada Y inicial del guardian)
      @param valor (Valor del guardian 
      (usado para detección de colisiones))
      @param tipo (Tipo de guardian)
      @param estaActivo (Indica si el guardian está activo)
      @param jugador (Referencia al jugador que el guardian
       seguirá)
	 **/
	
	public Guardian(int gx, int gy, int valor, String tipo,
	boolean estaActivo, Jugador jugador) {
		
		super();
		this.gx = gx;
		this.gy = gy;
		this.valor = valor;
		this.tipo = tipo;
		this.estaActivo = true;
		this.jugador = jugador;
		
	}

	/**
	 int "gx" albergara la coordenada X del guardian.
	 **/
	
	public int gx;
	
	/**
	 int "gy" albergara la coordenada Y del guardian.
	 **/
	
	public int gy;
	
	/**
	 int "valor" albergara el valor del guardian.
	 **/
	
	public int valor;
	
	/**
	 String "tipo" albergara tipo de guardian.
	 **/
	
	public String tipo;
	
	/**
	 boolean "estaActivo" indica si el guardian esta activo.
	 **/
	
	public boolean estaActivo;
	
	/**
	 Jugador "jugador" agrega la clase Jugador para 
	 referencias correspondientes al guardian.
	 **/
	
	private Jugador jugador;
	
	/**
	  @param mover
	  
	  <p> 
	  El metodo mover controla el movimiento del
	  guardian.
	  El guardian se mueve siguiendo la posición X del
	  jugador.
	  Si sale de los límites de la pantalla (0-500), se
	  reposiciona.</p>
	 **/
	
	public void mover() {
		
		if(jugador.getPosicionX() > gx) {
			
			gx = gx + 10;
			
		} else if(jugador.getPosicionX() < gx) {
			
			gx = gx - 10;
			
		}
		
		if(gx < 0) {
			
			gx = 500;
			
		} else if(gx > 500) {
			
		gx = 0;
			
		}
		
	}
	
	/**
	  @param verificarColision
	  
	  <p> 
	  El metodo verificarColision verifica si hay colisión
	  entre el guardian y el jugador. </p>
	  
	  @param jugador Jugador
	 **/
	
	public boolean verificarColision(Jugador jugador) {
		
	    return (jugador.getPosicionX() >= gx && 
	            jugador.getPosicionX() <= gx + valor &&
	            jugador.getPosicionY() >= gy && 
	            jugador.getPosicionY() <= gy + valor);
	    
	}
	
	/**
	  @param run
	  
	  <p> 
	  El metodo run mueve el guardian continuamente 
	  mientras esté activo. </p>
	  
	  @param jugador Jugador
	 **/
	
	@Override
	public void run() {
		
	    while (estaActivo) {
	    	
	        mover();
	        try {
	        	
	            Thread.sleep(50);
	        } catch (InterruptedException e) {
	        	
	            System.out.println
	            ("Error en el movimiento del Guardian");
	            break;
	            
	        }
	        
	    }
	    
	}
	
}