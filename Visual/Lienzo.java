package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import logica.Escenario;
import logica.Obstaculo;
import logica.Gema;

/**
<P> Descripcion: Esta clase gestiona el renderizado del 
juego, el movimiento del jugador, la simulación de física y
 el estado del juego. </p>

<P> Extiende JPanel e implementa KeyListener y Runnable 
para crear un entorno de juego interactivo. </p>
**/

public class Lienzo extends JPanel 
implements KeyListener, Runnable {
	
	/**
	 <p> Este campo se utiliza para asegurar que una clase
	 serializada y deserializada mantenga la compatibilidad.
	  Si una clase cambia y no se actualiza el 
	  serialVersionUID, puede resultar en una 
	  InvalidClassException durante la deserialización.</p>
	 **/
	
    private static final long serialVersionUID = 1L;
    
 /** 
  Escenario del juego que contiene los elementos del
  juego.
  **/
    
    private Escenario e = new Escenario();
    
 /** Constantes y parámetros de física **/
    
    private boolean moverIzquierda = false;
    private boolean moverDerecha = false;
    private boolean saltar = false;
    private final float gravedad = 1.0f;
    private final int velocidad_salto = -25;
    private final int velocidad_horizontal = 5;
    private float velocidad_vertical = 0;
    private final int impulsoSalto = -18;
    
    /** Bandera de estado del juego **/
    
    private boolean nivelCompletado = false;
    
    /**
	 Constructor para iniciar el lienzo.
	 @param Thread repintar (Inicializa el hilo de juego,
	  		establece el foco y añade el detector de 
	  		teclado.)
	 **/
    
    public Lienzo() {
    	
        Thread repintar = new Thread(this);
        repintar.start();
        setFocusable(true);
        addKeyListener(this);
        
    }
    
    /**
     @param paintComponent
   
   <p> Renderiza los elementos del juego en el panel:
   
   Dibuja:
     - Fondo blanco
     - Obstáculos de suelo y plataformas
     - Gema para pasar el nivel
     - Jugador
     - Mensaje de completado de nivel</p>
     
     @param g (utilizado para renderizar)
    **/
    
    /**
      El mensaje de completado de nivel solo debe existir
      en el Prototipo_Basico.
     **/
    
    @Override
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);

        // Fondo
        g.setColor(Color.white);
        g.fillRect(0, 0, e.ancho, e.alto);

        // Dibujar suelo (obstáculo principal)
        for (Obstaculo ob : e.listaObstaculo) {
        	
            if (ob != null && ob.estaActivo) {
            	
                if ("suelo".equals(ob.tipo)) {
                	
                    g.setColor(Color.red); 
                    g.fillRect(ob.x, ob.y, ob.valor, 50);
                    
                } else if ("plataforma".equals(ob.tipo)) {
                	
                    g.setColor(Color.green); 
                    g.fillRect(ob.x, ob.y, ob.valor, 10);
                    
                }
                
            }
            
        }

        // Dibujar gemas
        for (Gema gema : e.listaGema) {
        	
            if (gema != null && gema.estaActivo) {
            	
                g.setColor(Color.CYAN);
                
                int[] xPoints = {
                		
                    gema.gemx,             
                    gema.gemx + gema.valor,  
                    gema.gemx,             
                    gema.gemx - gema.valor 
                    
                };
                
                int[] yPoints = {
                		
                    gema.gemy,                                          
                    gema.gemy + gema.valor / 2,
                    gema.gemy + gema.valor,        
                    gema.gemy + gema.valor / 2 
                    
                };
                
                g.fillPolygon(xPoints, yPoints, 4);
                
            }
            
        }

        // Dibujar jugador
        g.setColor(Color.blue);
        g.fillRect(
            (int) e.jugador.getPosicionX(),
            (int) e.jugador.getPosicionY(),
            30, // Ancho del jugador
            30  // Alto del jugador
        );
        
        if (nivelCompletado) {
        	
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("¡Nivel Completado!", 100, 250);
            
        }
        
    }
    
    /**
    @param actualizarMovimiento
  
  <p> Actualiza el estado del juego, incluyendo 
  el movimiento y física del jugador.
  
  Gestiona:
    - Movimiento horizontal
    - Mecánica de salto
    - Aplicación de gravedad
    - Detección de colisiones con obstáculos y gemas
    - Mantener al jugador dentro de los límites de la
     pantalla</p>
  **/
    
    private void actualizarMovimiento() {
    	
        try {
        	
            // Movimiento horizontal
            if (moverIzquierda) {
            	
                e.jugador.setPosicionX
                (e.jugador.getPosicionX() 
                - velocidad_horizontal);
                
            }
            
            if (moverDerecha) {
            	
                e.jugador.setPosicionX
                (e.jugador.getPosicionX() 
                + velocidad_horizontal);
                
            }
            
            // Física del salto
            if (saltar && e.jugador.isEstaEnSuelo()) {
            	
                velocidad_vertical = impulsoSalto;
                e.jugador.setEstaEnSuelo(false);
                
            }
            
            // Aplica la gravedad
            velocidad_vertical += gravedad;
            
            e.jugador.setPosicionY(e.jugador.getPosicionY()
            + velocidad_vertical);

            // Verificar colisiones con obstáculos
            for (Obstaculo ob : e.listaObstaculo) {
            	
                if (ob != null && ob.estaActivo) {
                	
                    // Verificación de colisión más 
                	//estricta para el suelo
                	
                    if ("suelo".equals(ob.tipo)) {
                    	
                        if (e.jugador.getPosicionX() 
                        + 30 > ob.x &&
                        
                            e.jugador.getPosicionX() 
                            < ob.x + ob.valor &&
                            
                            e.jugador.getPosicionY() 
                            + 30 >= ob.y &&
                            
                            e.jugador.getPosicionY() 
                            + 30 <= ob.y + 50 &&
                            
                            velocidad_vertical >= 0) {
                            
                            e.jugador.setPosicionY
                            (ob.y - 30);
                            
                            velocidad_vertical = 0;
                            e.jugador.setEstaEnSuelo(true);
                            
                        }
                        
                    } else if ("plataforma".equals(ob.tipo))
                    {
                    	
                        if (e.jugador.getPosicionX() 
                        + 30 > ob.x &&
                        
                            e.jugador.getPosicionX() 
                            < ob.x + ob.valor &&
                            
                            e.jugador.getPosicionY() 
                            + 30 >= ob.y &&
                            
                            e.jugador.getPosicionY() 
                            + 30 <= ob.y + 20 &&
                            
                            velocidad_vertical >= 0) {
                            
                            e.jugador.setPosicionY
                            (ob.y - 30);
                            
                            velocidad_vertical = 0;
                            e.jugador.setEstaEnSuelo(true);
                            
                        }
                        
                    }
                    
                }
                
            }
            
            // Verificar colisión con gemas
            for (Gema gema : e.listaGema) {
            	
                if (gema != null && gema.estaActivo) {
                	
                    if (gema.verificarColision(e.jugador)) {
                    	
                        gema.estaActivo = false;
                        nivelCompletado = true;
                        
                    }
                    
                }
                
            }
            
            /* Mantener al jugador dentro de los límites 
            del escenario */
            
            e.jugador.setPosicionX
            (Math.max(0, Math.min(e.jugador.getPosicionX()
            , e.ancho - 30)));
            
            e.jugador.setPosicionY
            (Math.max(0, Math.min(e.jugador.getPosicionY(
            ), e.alto - 30)));

        } catch (Exception ex) {
        	
            System.err.println
            ("Error en actualizarMovimiento: " 
            + ex.getMessage());
            
            ex.printStackTrace();
            
        }
        
    }
    
    /**
    @param KeyPressed
      
  <p> Gestiona los eventos de pulsación de teclas para el
   movimiento del jugador.
  
  Permite:
    - El uso de teclas para movimiento horizontal
    - Barra espaciadora para saltar cuando está en 
      el suelo</p>
      
      @param e (Evento de teclado que representa 
      la pulsación de tecla)
     **/
    
    
    @Override
    public void keyPressed(KeyEvent e) {
    	
        switch (e.getKeyCode()) {
        
            case KeyEvent.VK_LEFT:
                moverIzquierda = true;
                break;
            case KeyEvent.VK_RIGHT:
                moverDerecha = true;
                break;
            case KeyEvent.VK_SPACE:
                if (this.e.jugador.isEstaEnSuelo()) {
                	
                    saltar = true;
                } 
                
                break;
                
        }
        
    }
    
    /**
    @param KeyReleased
      
  <p> Gestiona los eventos de liberación de teclas para
   detener el movimiento del jugador.
 
 	Quiebra todos los movimientos y el salto.
 
      @param e (Evento de teclado que representa 
      la pulsación de tecla)
     **/
    
    @Override
    public void keyReleased(KeyEvent e) {
    	
        switch (e.getKeyCode()) {
        
            case KeyEvent.VK_LEFT:
                moverIzquierda = false;
                break;
            case KeyEvent.VK_RIGHT:
                moverDerecha = false;
                break;
            case KeyEvent.VK_SPACE:
                saltar = false; 
                break;
                
        }
        
    }
    
    /**
    @param run
      
  <p> Bucle principal del juego ejecutándose en un
   hilo separado.
 
 	Continuamente:
 	
    - Actualiza el movimiento del juego
    - Redibuja el lienzo del juego
 
     **/
    
    @Override
    public void run() {
    	
        while (true) {
        	
            try {
            	
                actualizarMovimiento();
                repaint();
                Thread.sleep(16); // Aproximadamente 60 FPS
                
            } catch (InterruptedException ex) {
            	
                System.err.println
                ("Error en el hilo de repintado: " 
                + ex.getMessage());
                
                Thread.currentThread().interrupt();
                
            }
            
        }
        
    }
    
                 // Getters and Setters //
    
    public float getVelocidad_vertical() {
    	
        return velocidad_vertical;
        
    }
    
    public void setVelocidad_vertical
    (float velocidad_vertical) {
    	
        this.velocidad_vertical = velocidad_vertical;
        
    }

    public int getImpulsoSalto() {
    	
        return impulsoSalto;
        
    }

    public int getVelocidad_salto() {
    	
        return velocidad_salto;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    	
        // TODO Auto-generated method stub
    	
    }
    
}