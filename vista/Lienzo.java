package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import logica.Escenario;
import logica.Obstaculo;
import logica.Gema;
import logica.Guardian;
import persistencia.Persistencia;

/** 
<p> Descripcion: Esta clase representa lienzo visual del proyecto. </p>
**/
public class Lienzo extends JPanel implements KeyListener, Runnable {
	
	/** 
	<p> Descripcion: Esta clase representa un obstáculo en un 
	juego que puede detectar colisiones con un jugador.
	Implementa Runnable para potencial ejecución en un hilo 
	separado. </p>
	**/
    private static final long serialVersionUID = 1L;
    
    //Creacion del escenario a nivel visual//
    private Escenario e = new Escenario();
    
    //Valores Constantes//
    private boolean moverIzquierda = false;
    private boolean moverDerecha = false;
    private boolean saltar = false;
    private final float gravedad = 1.0f;
    private final int velocidad_salto = -25;
    private final int velocidad_horizontal = 5;
    private float velocidad_vertical = 0;
    private final int impulsoSalto = -18;
    private BufferedImage imgJugador;
    private BufferedImage imgSuelo;
    private BufferedImage imgGema;
    private BufferedImage imgGuardian;
    private boolean nivelCompletado = false;
    private boolean juegoTerminado = false;
    private String textoGuardado = "";
    private long tiempoTextoGuardado = 0;
	private BufferedImage imgFondo;
    
	/**
    Constructor por defecto que inicializa el lienzo.
    **/
    public Lienzo() {
    	
        Thread repintar = new Thread(this);
        repintar.start();
        setFocusable(true);
        addKeyListener(this);
        cargarImagenes();

    }
    
    /**
	  @param paintComponent
	  
	  <p> El metodo paintComponent renderiza los objetos. </p>
	  
	  @param Graphics g 
	 **/
    @Override
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);

     // Dibujar suelo y plataformas con desplazamiento de cámara
        for (Obstaculo ob : e.listaObstaculo) {
        	
            if (ob != null && ob.estaActivo) {
            	
                int renderX = ob.x - e.xLvlOffset;  //Mueve las plataformas con el desplazamiento de la cámara//
                if ("suelo".equals(ob.tipo)) {
                	
                    g.setColor(Color.red); 
                    g.fillRect(renderX, ob.y, ob.valor, 50);  //Dibuja el suelo//
                } else if ("plataforma".equals(ob.tipo)) {
                	
                    g.setColor(Color.green); 
                    g.fillRect(renderX, ob.y, ob.valor, 10); //Dibuja las plataformas//
                    
                }
                
            }
            
        }

        //Dibujar gemas con desplazamiento de cámara//
        for (Gema gema : e.listaGema) {
        	
            if (gema != null && gema.estaActivo) {
            	
                int renderX = gema.gemx - e.xLvlOffset;
                int[] xPoints = {renderX, renderX + gema.valor/2, renderX + gema.valor, renderX + gema.valor/2};
                int[] yPoints = {gema.gemy + gema.valor/2, gema.gemy, gema.gemy + gema.valor/2, gema.gemy + gema.valor};
                
                g.setColor(Color.CYAN);
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 4);
                
            }
            
        }
       
        //Dibujar guardianes//
        for (Guardian guardian : e.listaGuardian) {
        	
            if (guardian != null && guardian.estaActivo) {
            	
                int renderX = guardian.gx - e.xLvlOffset;
                if (renderX >= 0 && renderX <= e.ancho) {
                	
                    if (imgGuardian != null) {} else {
                    	
                        g.setColor(Color.gray);
                        g.fillRect(renderX, guardian.gy, 50, 50);
                        
                    }
                    
                } else {
                	
                    System.out.println("Guardian fuera del lienzo. Posición renderizada: (" + renderX + ", " + guardian.gy + ")");
                    
                }
                
            }
            
        }

        //Dibujar jugador//
        int renderX = (int) e.jugador.getPosicionX() - e.xLvlOffset;
        g.setColor(Color.BLUE);
        g.fillRect(renderX, (int) e.jugador.getPosicionY(), 40, 40); // Cuadrado azul

        if (nivelCompletado) {
        	
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("¡Nivel Completado!", 100, 250);
            
        }
        
        if (juegoTerminado) {
        	
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 100, 250);
            
            //Opción para reiniciar//
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Presiona R para reiniciar", 100, 300);
            
        }

        //Mostrar texto de guardado/cargado//
        if (!textoGuardado.isEmpty() && System.currentTimeMillis() - tiempoTextoGuardado < 2000) {
        	
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(textoGuardado, 10, 30);
            
        }
        
    }
    
    /**
	  @param actualizarMovimiento
	  
	  <p> 
	  El metodo actualizarMovimiento renderiza las fisicas en el lienzo. <\p>
	 **/
    private void actualizarMovimiento() {
    	
        try {
        	
            //Movimiento horizontal//
            if (moverIzquierda) {
            	
                e.jugador.setPosicionX(e.jugador.getPosicionX() - velocidad_horizontal);
                
            }
            
            if (moverDerecha) {
            	
                e.jugador.setPosicionX(e.jugador.getPosicionX() + velocidad_horizontal);
                
            }
            
            //Física del salto//
            if (saltar && e.jugador.isEstaEnSuelo()) {
            	
                velocidad_vertical = impulsoSalto;
                e.jugador.setEstaEnSuelo(false);
                
            }
            
            //Aplica la gravedad//
            velocidad_vertical += gravedad;
            
            e.jugador.setPosicionY(e.jugador.getPosicionY() + velocidad_vertical);

            //Verificar colisiones con obstáculos//
            for (Obstaculo ob : e.listaObstaculo) {
            	
                if (ob != null && ob.estaActivo) {
                	
                    if ("suelo".equals(ob.tipo)) {
                    	
                        if (e.jugador.getPosicionX() + 30 > ob.x &&
                            e.jugador.getPosicionX() < ob.x + ob.valor &&
                            e.jugador.getPosicionY() + 30 >= ob.y &&
                            e.jugador.getPosicionY() + 30 <= ob.y + 50 &&
                            velocidad_vertical >= 0) {
                        	
                            
                            e.jugador.setPosicionY(ob.y - 30);
                            velocidad_vertical = 0;
                            e.jugador.setEstaEnSuelo(true);
                            
                        }
                        
                    } else if ("plataforma".equals(ob.tipo)) {
                    	
                        if (e.jugador.getPosicionX() + 30 > ob.x &&
                            e.jugador.getPosicionX() < ob.x + ob.valor &&
                            e.jugador.getPosicionY() + 30 >= ob.y &&
                            e.jugador.getPosicionY() + 30 <= ob.y + 20 &&
                            velocidad_vertical >= 0) {
                            
                            e.jugador.setPosicionY(ob.y - 30);
                            velocidad_vertical = 0;
                            e.jugador.setEstaEnSuelo(true);
                            
                        }
                        
                    }
                    
                }
                
            }
            
            //Verificar colisión con gemas//
            for (Gema gema : e.listaGema) {
            	
                if (gema != null && gema.estaActivo) {
                	
                    if (gema.verificarColision(e.jugador)) {
                    	
                        gema.estaActivo = false;
                        nivelCompletado = true;
                        
                    }
                    
                }
                
            }
            
            //Actualizar offset de la cámara//
            e.updateCameraOffset();
            
            //Mantener al jugador dentro de los límites del escenario//
            e.jugador.setPosicionX(
                Math.max(0, 
                Math.min(e.jugador.getPosicionX(), e.MAX_WORLD_WIDTH - 30))
            );
            
            e.jugador.setPosicionY(
                Math.max(0, 
                Math.min(e.jugador.getPosicionY(), e.alto - 30))
            );

        } catch (Exception ex) {}
        
    }
    
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
            case KeyEvent.VK_G:
                Persistencia.guardar(this.e, this);
                break;
            case KeyEvent.VK_B:
                Persistencia.cargar(this.e, this);
                break;
                
        }
        
    }
    
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
    
    @Override
    public void run() {
    	
        while (true) {
        	
            try {
            	
                actualizarMovimiento();
                repaint();
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException ex) {
            	
                System.err.println("Error en el hilo de repintado: " + ex.getMessage());
                Thread.currentThread().interrupt();
                
            }
            
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void mostrarTextoGuardado(String texto) {
    	
        textoGuardado = texto;
        tiempoTextoGuardado = System.currentTimeMillis();
        repaint(); // Asegurar que se redibuje para mostrar el texto
        
    }

     // Getters and Setters //
    
    public int getVelocidad_salto() {
    	
        return velocidad_salto;
        
    }

	public BufferedImage getImgJugador() {
		
		return imgJugador;
		
	}

	public void setImgJugador(BufferedImage imgJugador) {
		
		this.imgJugador = imgJugador;
		
	}

	public BufferedImage getImgGema() {
		
		return imgGema;
		
	}

	public void setImgGema(BufferedImage imgGema) {
		
		this.imgGema = imgGema;
		
	}

	public BufferedImage getImgGuardian() {
		
		return imgGuardian;
		
	}

	public void setImgGuardian(BufferedImage imgGuardian) {
		
		this.imgGuardian = imgGuardian;
		
	}

private void cargarImagenes() {
	
    try {
    	
        imgJugador = ImageIO.read(new File("resources/jugador.gif"));
        imgGema = ImageIO.read(new File("resources/gema.png"));
        imgGuardian = ImageIO.read(new File("resources/guardian.png"));
        setImgFondo(ImageIO.read(new File("resources/fondo.jpg")));
    } catch (IOException ex) {
    	
        System.err.println("Error cargando imágenes: " + ex.getMessage());
        
    }
    
}

public BufferedImage getImgFondo() {
	
	return imgFondo;
	
}

public void setImgFondo(BufferedImage imgFondo) {
	
	this.imgFondo = imgFondo;
	
}

public BufferedImage getImgSuelo() {
	
	return imgSuelo;
	
}

public void setImgSuelo(BufferedImage imgSuelo) {
	
	this.imgSuelo = imgSuelo;
	
}

}