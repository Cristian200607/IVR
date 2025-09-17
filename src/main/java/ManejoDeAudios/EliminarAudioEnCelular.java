package ManejoDeAudios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EliminarAudioEnCelular {

    public static void ejecutar(String udid, String rutaCelular) {
        try {
            // Aseguramos que se eliminen todos los archivos dentro del directorio
            String comando = String.format("adb -s %s shell rm -f %s/*", udid, rutaCelular);

            System.out.println("Ejecutando comando: " + comando);

            Process proceso = Runtime.getRuntime().exec(comando);
            int resultado = proceso.waitFor();

            if (resultado == 0) {
                System.out.println("Archivos eliminados en el celular: " + rutaCelular);
            } else {
                // Capturar STDERR para ver el error real
                BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                StringBuilder errorMsg = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    errorMsg.append(line).append("\n");
                }
                throw new RuntimeException("Error al eliminar archivos en el celular. Código: "
                        + resultado + " - Detalle: " + errorMsg);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error ejecutando adb rm: " + e.getMessage(), e);
        }
    }
}
