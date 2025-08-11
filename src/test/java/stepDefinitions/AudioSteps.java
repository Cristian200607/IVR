package stepDefinitions;

import io.cucumber.java.en.*;
import java.io.File;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.junit.jupiter.api.Assertions.*;

import tasks.LimpiarYRespaldarAudio;
import tasks.TraerAudioDesdeCelular;
import utils.AudioConverter;
import utils.SpeechToTextExample;

public class AudioSteps {

    private String inputAudioPath;
    private String convertedAudioPath;
    private String transcription;


    @Given("El archivo de audio se trae automáticamente desde el celular")
    public void givenTraerArchivoDesdeCelular() {
        String rutaCelular = "/sdcard/Recordings/Call/";
        String rutaLocal = "C:\\IVR\\Llamadas\\";
        TraerAudioDesdeCelular.desde(rutaCelular, rutaLocal);
    }



    @And("Un archivo de audio en formato OGG ubicado en Llamadas")
    public void givenArchivoAudio() {
        File folder = new File("Llamadas/Call");
        File[] archivos = folder.listFiles((dir, name) -> {
            // Acepta cualquier archivo que no sea carpeta (puedes afinar si quieres solo ciertos formatos)
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
        } else {
            throw new RuntimeException("No se encontró ningún archivo de audio en Llamadas/Call");
        }
    }

    @When("Se convierte el archivo a WAV mono 16kHz")
    public void cuandoSeConvierteAudio() throws Exception {
        AudioConverter.convertToWav(inputAudioPath, convertedAudioPath);
    }


    @And("Se realiza el reconocimiento de voz sobre el archivo convertido en la carpeta {string}")
    public void cuandoSeReconoceAudio(String rutaCarpeta) throws Exception {
        // Aquí buscas el archivo dentro de la carpeta (por ejemplo, el único archivo .wav)
        File carpeta = new File(rutaCarpeta);
        File[] archivos = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));

        if (archivos == null || archivos.length == 0) {
            throw new RuntimeException("No se encontró ningún archivo WAV en la carpeta: " + rutaCarpeta);
        }

        // Por ejemplo, tomar el primero (o el último modificado)
        File archivoParaReconocer = archivos[0];

        transcription = SpeechToTextExample.recognize(archivoParaReconocer.getAbsolutePath());
    }

    @Then("Visualizacion de la trancripcion")
    public void entoncesVisualizarTranscripcion() {
        assertNotNull(transcription, "La transcripción no debe ser nula");
        System.out.println("=== Transcripción del audio ===");
        System.out.println(transcription);
        System.out.println("===============================");

        LimpiarYRespaldarAudio.ejecutar(
                "Llamadas/Call", convertedAudioPath, "Llamadas/BackupsAudio"
        );
    }


}
