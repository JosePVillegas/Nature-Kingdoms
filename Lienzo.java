package visual;

import logica.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Lienzo extends JPanel implements KeyListener {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Jugador jugador;
	private Entorno entorno;

	public Lienzo() {
		
		jugador = new Jugador();
		setEntorno(new Entorno());
		
        Dimension screenSize = Toolkit.getDefaultToolkit().
        getScreenSize();
        this.setPreferredSize(screenSize);
		this.addKeyListener(this);
		this.setFocusable(true);
		
	}
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		g.setColor (Color.BLUE);
		g.fillRect(jugador.getPosicionX(), 
		jugador.getPosicionY(),50, 50);
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key) {
		
		case KeyEvent.VK_LEFT:
			jugador.mover();
			break;
			
		case KeyEvent.VK_RIGHT:
			jugador.mover();
			break;
			
		case KeyEvent.VK_SPACE:
			jugador.saltar();
			break;
		
		}
		
	}
		
	public void keyReleased(KeyEvent e) {}
	
	public void KeyTyped(KeyEvent e) {}

	public Entorno getEntorno1() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

	public Entorno getEntorno() {
		// TODO Auto-generated method stub
		return null;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
