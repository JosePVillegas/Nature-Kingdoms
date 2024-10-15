package logica;

public abstract class Entidad_ca {

	private int posicionX;
	private int posicionY;
	protected int velocidad;
	protected int vida;
	
	public void mover() {}
	public void correr() {}
	public void saltar() {}
	public void atacar() {}
	
	public int getPosicionX() {
		return posicionX;
	}
	
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
		
	}
	
	public int getPosicionY() {
		return posicionY;
		
	}
	
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
		
	}
	
	
}
