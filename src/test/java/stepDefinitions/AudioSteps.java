package stepDefinitions;

import cucumber.api.java.en.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import ManejoDeAudios.EliminarAudioEnCelular;
import ManejoDeAudios.LimpiarYRespaldarAudio;
import ManejoDeAudios.TraerAudioDesdeCelular;
import ManejoDeAudios.AudioConverter;
import ManejoDeAudios.SpeechToTextIVR;

public class AudioSteps {

    private String inputAudioPath;
    private String convertedAudioPath;
    public static String transcription;

    @Given("^El archivo de audio se trae automáticamente desde el celular$")
    public void givenTraerArchivoDesdeCelular() {
        String rutaCelular = "/sdcard/Recordings/Call/";
        String rutaLocal = "C:\\IVR\\Llamadas\\";
        TraerAudioDesdeCelular.desde(rutaCelular, rutaLocal);
    }

    @And("^Un archivo de audio ubicado en Llamadas$")
    public void givenArchivoAudio() {
        File folder = new File("Llamadas/Call");
        File[] archivos = folder.listFiles((dir, name) -> {
            File f = new File(dir, name);
            return f.isFile();
        });

        if (archivos != null && archivos.length > 0) {
            inputAudioPath = archivos[0].getPath().replace("\\", "/");
            String nombreArchivo = archivos[0].getName();
            String nombreSinExtension = nombreArchivo.contains(".") ?
                    nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.')) : nombreArchivo;

            convertedAudioPath = "LlamadasConvertidas/" + nombreSinExtension + ".wav";

            File carpetaConvertidas = new File("LlamadasConvertidas");
            if (!carpetaConvertidas.exists()) {
                carpetaConvertidas.mkdirs();
            }

            // 🔹 Condicional: si ya es WAV, no convertir
            if (nombreArchivo.toLowerCase().endsWith(".wav")) {
                System.out.println("El archivo ya es WAV: " + inputAudioPath);
                convertedAudioPath = inputAudioPath;
            }

        } else {
            throw new RuntimeException("No se encontró ningún archivo de audio en Llamadas/Call");
        }
    }

    @When("^Se convierte el archivo a WAV mono 16kHz si es necesario$")
    public void cuandoSeConvierteAudio() throws Exception {
        // 🔹 Solo convertir si no es WAV
        if (!inputAudioPath.toLowerCase().endsWith(".wav")) {
            AudioConverter.convertToWav(inputAudioPath, convertedAudioPath);
        }
    }

    @And("^Se realiza el reconocimiento de voz sobre el archivo convertido$")
    public void cuandoSeReconoceAudio() throws Exception {
        File archivoParaReconocer = new File(convertedAudioPath);

        if (!archivoParaReconocer.exists()) {
            throw new RuntimeException("No se encontró el archivo WAV para reconocimiento: " + convertedAudioPath);
        }

        transcription = SpeechToTextIVR.recognize(archivoParaReconocer.getAbsolutePath());
    }

    @And("^Visualizacion de la transcripcion$")
    public void entoncesVisualizarTranscripcion() {
        assertNotNull(transcription, "La transcripción no debe ser nula");
        System.out.println("=== Transcripción del audio ===");
        System.out.println(transcription);
        System.out.println("===============================");

        LimpiarYRespaldarAudio.ejecutar(
                "Llamadas/Call", convertedAudioPath, "Llamadas/BackupsAudio"
        );

        // 🔥 Elimina los audios en el celular para que no se acumulen
        EliminarAudioEnCelular.ejecutar("/sdcard/Recordings/Call/");
    }

}
