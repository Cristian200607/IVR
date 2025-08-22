package ManejoDeAudios;

import java.io.File;
import java.io.IOException;

public class SincronizarAudio {

    // Nuevo método para sincronizar desde el celular a la PC
    public static void desde(String rutaCelular, String rutaPC, int tiempoEsperaMs) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Asegurar que la carpeta local exista
                File carpetaDestino = new File(rutaPC);
                if (!carpetaDestino.exists()) {
                    carpetaDestino.mkdirs();
                }

                // Ejecutar adb pull
                String comando = String.format("adb pull %s* %s", rutaCelular, rutaPC);
                Process proceso = Runtime.getRuntime().exec(comando);
                int resultado = proceso.waitFor();

                if (resultado == 0) {
                    System.out.println("Audio sincronizado correctamente");
                } else {
                    System.err.println("Error al sincronizar audio: " + resultado);
                }

                // Esperar antes del siguiente pull
                Thread.sleep(tiempoEsperaMs);

            } catch (IOException | InterruptedException e) {
                System.err.println("Error sincronizando audio: " + e.getMessage());
                Thread.currentThread().interrupt(); // salir si hay interrupción
            }
        }
    }
}
