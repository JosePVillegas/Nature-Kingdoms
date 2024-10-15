package logica;

public class Entorno {

	private Jugador jugador;
    private Enemigo[] enemigo;
    private Puzzle[] puzzles;
	
    public void cargarEscenario() {}
    public void actualizarEscenario() {}
    public void activarLuz() {}
    public void desactivarLuz() {}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Enemigo[] getEnemigo() {
		return enemigo;
	}
	public void setEnemigo(Enemigo[] enemigo) {
		this.enemigo = enemigo;
	}
	public Puzzle[] getPuzzles() {
		return puzzles;
	}
	public void setPuzzles(Puzzle[] puzzles) {
		this.puzzles = puzzles;
	}
    
}
