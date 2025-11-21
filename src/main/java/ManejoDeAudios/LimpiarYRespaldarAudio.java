package ManejoDeAudios;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class LimpiarYRespaldarAudio {

    /**
     * Mueve todos los archivos de la carpeta original a la carpeta de backup
     * y luego limpia la carpeta original.
     *
     * @param carpetaOriginal Carpeta con audios a respaldar y eliminar (ej: "Llamadas/Call")
     * @param carpetaBackup Carpeta destino para mover los audios (ej: "Llamadas/BackupsAudio")
     */
    public static void ejecutar(String carpetaOriginal, String carpetaBackup) {
        try {
            File dirOriginal = new File(carpetaOriginal);
            if (!dirOriginal.exists() || !dirOriginal.isDirectory()) {
                System.out.println("La carpeta original no existe o no es un directorio: " + carpetaOriginal);
                return;
            }

            // Crear carpeta backup si no existe
            File dirBackup = new File(carpetaBackup);
            if (!dirBackup.exists()) {
                boolean creada = dirBackup.mkdirs();
                System.out.println("Carpeta backup creada: " + creada);
            }

            // Mover todos los archivos a backup
            File[] archivos = dirOriginal.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        Path origen = archivo.toPath();
                        Path destino = Paths.get(carpetaBackup, archivo.getName());
                        Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Archivo movido a backup: " + destino.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al respaldar y limpiar archivos: " + e.getMessage(), e);
        }
    }
}
