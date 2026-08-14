package ManejoDeAudios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TraerAudioDesdeCelular {

    public static void desde(String udid, String rutaCelular, String rutaLocal) {

        try {

            // Crear carpeta local
            File carpetaDestino = new File(rutaLocal);

            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
            }

            // Buscar únicamente archivos record-*.wav
            String comandoBuscar = String.format(
                    "adb -s %s shell find %s -type f -name \"record-*.wav\"",
                    udid,
                    rutaCelular
            );

            System.out.println("Buscando grabaciones con:");
            System.out.println(comandoBuscar);

            Process procesoBuscar = Runtime.getRuntime().exec(comandoBuscar);

            java.io.BufferedReader reader =
                    new java.io.BufferedReader(
                            new java.io.InputStreamReader(
                                    procesoBuscar.getInputStream()
                            )
                    );

            List<String> archivos = reader.lines()
                    .filter(linea -> !linea.trim().isEmpty())
                    .collect(Collectors.toList());

            procesoBuscar.waitFor();

            if (archivos.isEmpty()) {
                throw new RuntimeException(
                        "No se encontró ninguna grabación record-*.wav en "
                                + rutaCelular
                );
            }

            // Tomar la última grabación encontrada
            String archivoRemoto = archivos.get(archivos.size() - 1).trim();

            System.out.println(
                    "Grabación encontrada: " + archivoRemoto
            );

            // Obtener nombre del archivo
            String nombreArchivo =
                    Paths.get(archivoRemoto).getFileName().toString();

            String rutaDestino =
                    new File(rutaLocal, nombreArchivo).getAbsolutePath();

            // Traer únicamente el archivo WAV
            String comandoPull = String.format(
                    "adb -s %s pull \"%s\" \"%s\"",
                    udid,
                    archivoRemoto,
                    rutaDestino
            );

            System.out.println(
                    "Ejecutando comando: " + comandoPull
            );

            Process procesoPull =
                    Runtime.getRuntime().exec(comandoPull);

            int resultado = procesoPull.waitFor();

            if (resultado == 0) {

                System.out.println(
                        "Archivo copiado correctamente: "
                                + rutaDestino
                );

            } else {

                throw new RuntimeException(
                        "Error al copiar el audio. Código: "
                                + resultado
                );
            }

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Error ejecutando adb para traer el audio: "
                            + e.getMessage(),
                    e
            );
        }
    }
}