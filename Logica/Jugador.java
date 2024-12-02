package logica;

/** 
<p> Descripcion: Esta clase representa un jugador en un 
juego, con propiedades de movimiento, estado y visualización.
</p>
**/

public class Jugador {
	
	/**
	 Constructor para iniciar el jugador.
	 @param spiteSheet (Ruta o identificador de la hoja de 
	 sprites del jugador)
	 
     @param vida (Puntos de vida iniciales 
     (se sobrescribe a 1))
     
     @param posicionX (Posición inicial en el eje X)
     @param posicionY (Posición inicial en el eje Y)
     @param velocidad (Velocidad de movimiento 
     (se sobrescribe a 5))
     
     @param estaEnSuelo (Indica si el jugador está en el 
     suelo (se sobrescribe a false))
     
     @param estadoActual (Estado de movimiento inicial 
     (se sobrescribe a QUIETO))
	 **/
	
	public Jugador(String spiteSheet, int vida,
	float posicionX, float posicionY, float velocidad,
	boolean estaEnSuelo, EstadoMovimiento estadoActual) {
		
		super();
		this.spiteSheet = spiteSheet;
		this.vida = 1;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.velocidad = 5f;
		this.estaEnSuelo = false;
		this.estadoActual = EstadoMovimiento.QUIETO;
		this.color = "blue";
		
	}
	
	/**
	 String "spiteSheet" Hoja de sprites del jugador.
	 **/
	
	private String spiteSheet;
	
	/**
	 int "vida" Puntos de vida del jugador.
	 **/
	
	private int vida;
	
	/**
	 float "posicionX" Posición en el eje X.
	 **/
	
	private float posicionX;
	
	/**
	 float "posicionY" Posición en el eje Y.
	 **/
	
	private float posicionY;
	
	/**
	 float "velocidad" Velocidad de movimiento.
	 **/
	
	private float velocidad;
	
	/**
	 boolean "estaEnSuelo" 
	 Indica si el jugador está en el suelo.
	 **/
	
	private boolean estaEnSuelo;
	
	/**
	 enum "EstadoMovimiento" Enumeración de los posibles
	  estados de movimiento del jugador.
	 **/
	
	private enum EstadoMovimiento {
		
		QUIETO, CORRIENDO, SALTANDO, CAYENDO
		
	}
	
	/**
	 "estadoActual" Estado actual de movimiento del jugador.
	 **/
	
	private EstadoMovimiento estadoActual;
	
	/**
	 Object "color" Color del jugador 
	 (tipo Object para flexibilidad).
	 **/
	
	public Object color;
	
                 // Getters and Setters //
	
	public String getSpiteSheet() {
		
		return spiteSheet;
		
	}

	public void setSpiteSheet(String spiteSheet) {
		
		this.spiteSheet = spiteSheet;
		
	}

	public int getVida() {
		
		return vida;
		
	}

	public void setVida(int vida) {
		
		this.vida = vida;
		
	}

	public float getPosicionX() {
		
		return posicionX;
		
	}

	public void setPosicionX(float posicionX) {
		
		this.posicionX = posicionX;
		
	}

	public float getPosicionY() {
		
		return posicionY;
		
	}

	public void setPosicionY(float posicionY) {
		
		this.posicionY = posicionY;
		
	}

	public float getVelocidad() {
		
		return velocidad;
		
	}

	public void setVelocidad(float velocidad) {
		
		this.velocidad = velocidad;
		
	}

	public boolean isEstaEnSuelo() {
		
		return estaEnSuelo;
		
	}

	public void setEstaEnSuelo(boolean estaEnSuelo) {
		
		this.estaEnSuelo = estaEnSuelo;
		
	}

	public EstadoMovimiento getEstadoActual() {
		
		return estadoActual;
		
	}

	public void setEstadoActual
	(EstadoMovimiento estadoActual) {
		
		this.estadoActual = estadoActual;
		
	}
	
}