package ManejoDeAudios;

import interactions.comunes.WaitFor;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraerAudioDesdeCelular {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraerAudioDesdeCelular.class);

    public static void desde(String udid, String rutaCelular, String rutaLocal) {
        WaitFor.aTime(4000);
        try {
            // Asegurar que la carpeta local exista
            File carpetaDestino = new File(rutaLocal);
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs();
            }

            // Comando adb pull con UDID
            String comando = String.format("adb -s %s pull %s \"%s\"", udid, rutaCelular, rutaLocal);

            LOGGER.info("Ejecutando comando: {}", comando);


            Process proceso = Runtime.getRuntime().exec(comando);

            int resultado = proceso.waitFor();

            if (resultado == 0) {
                LOGGER.info("Archivo(s) copiado(s) correctamente desde el celular con UDID:  {}", udid);
            } else {
                LOGGER.info("Error al copiar archivo(s) desde el celular. Código:  {}", resultado);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error ejecutando adb pull: " + e.getMessage(), e);
        }
    }
}
