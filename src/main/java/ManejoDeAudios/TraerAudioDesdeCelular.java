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

    private static final long ESPERA_MAXIMA_MS = 20_000;
    private static final long INTERVALO_BUSQUEDA_MS = 500;

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

            String archivoRemoto = esperarGrabacion(udid, rutaCelular, comandoBuscar);

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

            // Con argumentos separados: en Mac/Linux las comillas de comandoPull llegarían literales
            Process procesoPull =
                    Runtime.getRuntime().exec(new String[] {
                            "adb", "-s", udid, "pull", archivoRemoto, rutaDestino
                    });

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

    /**
     * Files de Google guarda el audio segundos después del toque en "Files de Google", así que se
     * busca hasta que aparezca y su tamaño deje de cambiar (para no traerlo a medio copiar).
     */
    private static String esperarGrabacion(String udid, String rutaCelular, String comandoBuscar)
            throws IOException, InterruptedException {

        long limite = System.currentTimeMillis() + ESPERA_MAXIMA_MS;
        String archivoAnterior = null;
        long tamanoAnterior = -1;

        while (true) {

            List<String> archivos = ejecutar(comandoBuscar);

            if (!archivos.isEmpty()) {

                // Tomar la última grabación encontrada
                String archivo = archivos.get(archivos.size() - 1).trim();
                long tamano = tamanoEnCelular(udid, archivo);

                if (tamano > 0 && tamano == tamanoAnterior && archivo.equals(archivoAnterior)) {
                    return archivo;
                }

                archivoAnterior = archivo;
                tamanoAnterior = tamano;
            }

            if (System.currentTimeMillis() >= limite) {
                throw new RuntimeException(
                        archivoAnterior == null
                                ? "No se encontró ninguna grabación record-*.wav en " + rutaCelular
                                : "La grabación no terminó de guardarse: " + archivoAnterior
                );
            }

            Thread.sleep(INTERVALO_BUSQUEDA_MS);
        }
    }

    private static long tamanoEnCelular(String udid, String archivo)
            throws IOException, InterruptedException {

        List<String> salida = ejecutar(
                String.format("adb -s %s shell stat -c %%s %s", udid, archivo)
        );

        try {
            return salida.isEmpty() ? -1 : Long.parseLong(salida.get(0).trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static List<String> ejecutar(String comando)
            throws IOException, InterruptedException {

        Process proceso = Runtime.getRuntime().exec(comando);

        java.io.BufferedReader reader =
                new java.io.BufferedReader(
                        new java.io.InputStreamReader(
                                proceso.getInputStream()
                        )
                );

        List<String> lineas = reader.lines()
                .filter(linea -> !linea.trim().isEmpty())
                .collect(Collectors.toList());

        proceso.waitFor();

        return lineas;
    }
}