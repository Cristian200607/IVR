package ManejoDeAudios;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EliminarAudioEnCelular {

    /**
     * Elimina todos los archivos dentro de la carpeta del celular indicada
     *
     * @param rutaCarpetaCelular Ruta completa en el celular (ej: "/sdcard/Recordings/Call/")
     */
    public static void ejecutar(String rutaCarpetaCelular) {
        try {
            // Comando adb para borrar todos los archivos dentro de la carpeta (el * borra todo)
            String comando = String.format("adb shell rm %s*", rutaCarpetaCelular);

            Process process = Runtime.getRuntime().exec(comando);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Error al eliminar archivos en el celular. Código: " + exitCode);
            }

            System.out.println("Archivos eliminados correctamente en la carpeta del celular: " + rutaCarpetaCelular);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error ejecutando comando adb para eliminar archivos en celular: " + e.getMessage(), e);
        }
    }
}
