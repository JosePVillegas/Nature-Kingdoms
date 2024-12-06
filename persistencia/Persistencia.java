package persistencia;

import java.io.*;
import logica.Escenario;
import vista.Lienzo;

public abstract class Persistencia implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private static final String ARCHIVO_GUARDADO = "juego_guardado.ser";

    public static void guardar(Escenario escenario, Lienzo lienzo) {
        try (FileOutputStream fos = new FileOutputStream(ARCHIVO_GUARDADO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(escenario);
            lienzo.mostrarTextoGuardado("Juego guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error guardando el juego: " + e.getMessage());
            lienzo.mostrarTextoGuardado("Error al guardar el juego.");
        }
    }

    public static void cargar(Escenario escenario, Lienzo lienzo) {
        try (FileInputStream fis = new FileInputStream(ARCHIVO_GUARDADO);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Escenario cargado = (Escenario) ois.readObject();
            copiarEstado(escenario, cargado);

            lienzo.mostrarTextoGuardado("Juego cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error cargando el juego: " + e.getMessage());
            lienzo.mostrarTextoGuardado("Error al cargar el juego.");
        }
    }

    private static void copiarEstado(Escenario original, Escenario cargado) {
        original.jugador = cargado.jugador;
        original.listaObstaculo = cargado.listaObstaculo;
        original.listaGuardian = cargado.listaGuardian;
        original.listaGema = cargado.listaGema;
        original.xLvlOffset = cargado.xLvlOffset;
    }
}
