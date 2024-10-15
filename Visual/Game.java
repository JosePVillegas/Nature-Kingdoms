package visual;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Nature Kingdoms");
		Lienzo game = new Lienzo();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        
        game.getEntorno().cargarEscenario();
		
	}
	
}
