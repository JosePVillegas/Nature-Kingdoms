package vista;

import javax.swing.JFrame;

/**
<P> Descripcion: Clase principal que crea una ventana gráfica
 para la prototipo basico "Nature Kingdoms". </p>

<P> Esta clase extiende JFrame para crear una interfaz gráfica
 de usuario (GUI) y configura una ventana base 
 para la aplicación.. </p>

@author JP Villegas
@version 2.5.1
@since December 2024

**/

public class Main extends JFrame {
	
    /**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización.</p>
	 **/
	
	private static final long serialVersionUID = 1L;
	
	 /**
      <p> Constructor de la clase Main.
     
     Configura las propiedades básicas de la ventana:
     
     - Establece el título de la ventana
     - Define el tamaño de la ventana (500x500 píxeles)
     - Configura la operación de cierre por defecto
     - Crea un lienzo (Lienzo) y lo añade a la ventana
     - Centra la ventana en la pantalla
     - Hace la ventana visible </p>
     **/
	
	public Main() {
    	
        setTitle("Nature Kingdoms (PB)");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Lienzo lienzo = new Lienzo();
        add(lienzo);
        
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
	/**
     @param main
     
     <p> Utiliza SwingUtilities.invokeLater 
     para asegurar que la creación de la interfaz gráfica
     se realice en el Event Dispatch Thread, lo cual es 
     una práctica recomendada para aplicaciones Swing. </p>
     
     @param args Argumentos de línea de comandos 
     (no utilizados en esta aplicación)
     **/
	
    public static void main(String[] args) {
    	
        javax.swing.SwingUtilities.invokeLater(() -> {
        	
            new Main();
            
        });
        
    }
    
}