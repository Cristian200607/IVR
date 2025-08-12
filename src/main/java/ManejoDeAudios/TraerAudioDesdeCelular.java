package ManejoDeAudios;

import interactions.comunes.WaitFor;

import java.io.File;
import java.io.IOException;

public class TraerAudioDesdeCelular {

    public static void desde(String rutaCelular, String rutaLocal) {
        WaitFor.aTime(4000);
        try {
            // Asegurar que la carpeta local exista
            File carpetaDestino = new File(rutaLocal);
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
            }

            // Comando adb pull
            String comando = String.format("adb pull %s %s", rutaCelular, rutaLocal);

            System.out.println("Ejecutando comando: " + comando);

            Process proceso = Runtime.getRuntime().exec(comando);

            int resultado = proceso.waitFor();

            if (resultado == 0) {
                System.out.println("Archivo(s) copiado(s) correctamente desde el celular.");
            } else {
                System.err.println("Error al copiar archivo(s) desde el celular. Código: " + resultado);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error ejecutando adb pull: " + e.getMessage(), e);
        }
    }
}
