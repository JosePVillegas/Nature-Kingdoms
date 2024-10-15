package logica;

public class Enemigo extends Entidad_ca {

	private String estado;
	
	public void patrullar() {}
	public void serDerrotado() {}
	public String getEstado() {
		return estado;
	
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

