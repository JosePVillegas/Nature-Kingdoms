package logica;

import java.io.IOException;
import java.io.Serializable;

/** 
<p> Descripcion: Esta clase representa un jugador en un 
juego, con propiedades de movimiento, estado y visualización. </p>
**/

public class Jugador implements Serializable {
	
	/**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización. </p>
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 Constructor para iniciar el jugador.
     @param vida (Puntos de vida iniciales 
     (se sobrescribe a 1))
     @param posicionX (Posición inicial en el eje X)
     @param posicionY (Posición inicial en el eje Y)
     @param velocidad (Velocidad de movimiento 
     (se sobrescribe a 5))
     @param estaEnSuelo (Indica si el jugador está en el 
     suelo (se sobrescribe a false))
	 **/
	public Jugador(int vida,
	float posicionX, float posicionY, float velocidad,
	boolean estaEnSuelo) {
		
		super();
		this.vida = 1;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.estaEnSuelo = false;
		this.color = "blue";
		
	}
	
	/**
	 int "vida" albergara la vida del jugador.
	 **/
	private int vida;
	
	/**
	 float "posicionX" albergara las coordendas en el eje X.
	 **/
	private float posicionX;
	
	/**
	 float "posicionY" albergara las coordendas en el eje X.
	 **/
	private float posicionY;
	
	
	/**
	 boolean "EstaEnSuelo" verifica si el jugador esta en el suelo.
	 **/
	private boolean estaEnSuelo;
	
	/**
	 Object "color" albergara el color del jugador.
	 **/
	public Object color;
	
                     // Persistencia //
	
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    	
        out.defaultWriteObject();
        
    }
    
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
    	
        in.defaultReadObject();
        
    }
	
                 // Getters and Setters //
	
	public int getVida() {
		
		return vida;
		
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

	public boolean isEstaEnSuelo() {
		
		return estaEnSuelo;
		
	}

	public void setEstaEnSuelo(boolean estaEnSuelo) {
		
		this.estaEnSuelo = estaEnSuelo;
		
	}
	
}