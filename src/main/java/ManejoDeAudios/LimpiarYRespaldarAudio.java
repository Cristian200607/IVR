package ManejoDeAudios;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class LimpiarYRespaldarAudio {

    /**
     * Elimina todos los archivos dentro de la carpeta original (Llamadas/Call)
     * y mueve el archivo convertido a la carpeta BackupsAudio.
     *
     * @param carpetaOriginal Ruta de la carpeta original con audios a eliminar (ej: "Llamadas/Call")
     * @param archivoConvertido Ruta completa del archivo convertido (ej: "LlamadasConvertidas/audio.wav")
     * @param carpetaBackup Carpeta destino para mover el archivo convertido (ej: "Llamadas/BackupsAudio")
     */
    public static void ejecutar(String carpetaOriginal, String archivoConvertido, String carpetaBackup) {
        try {
            // 1. Eliminar archivos dentro de carpeta original
            File dirOriginal = new File(carpetaOriginal);
            if (dirOriginal.exists() && dirOriginal.isDirectory()) {
                File[] archivos = dirOriginal.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            boolean eliminado = archivo.delete();
                            System.out.println("Archivo eliminado: " + archivo.getName() + " -> " + eliminado);
                        }
                    }
                }
            } else {
                System.out.println("La carpeta original no existe o no es un directorio: " + carpetaOriginal);
            }

            // 2. Crear carpeta backup si no existe
            File dirBackup = new File(carpetaBackup);
            if (!dirBackup.exists()) {
                boolean creada = dirBackup.mkdirs();
                System.out.println("Carpeta backup creada: " + creada);
            }

            // 3. Mover archivo convertido a carpeta backup
            Path origen = Paths.get(archivoConvertido);
            if (!Files.exists(origen)) {
                System.out.println("Archivo convertido no existe: " + archivoConvertido);
                return;
            }

            Path destino = Paths.get(carpetaBackup, origen.getFileName().toString());
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido a backup: " + destino.toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al limpiar y respaldar archivos: " + e.getMessage(), e);
        }
    }
}
