package stepDefinitions;

import ManejoDeAudios.Whisper.SpeechToTextWhisperPython;
import cucumber.api.java.en.*;
import java.io.File;

import static coloresTerminal.colores.BLUE;
import static coloresTerminal.colores.RESET;
import static org.junit.jupiter.api.Assertions.*;

import ManejoDeAudios.EliminarAudioEnCelular;
import ManejoDeAudios.LimpiarYRespaldarAudio;
import ManejoDeAudios.TraerAudioDesdeCelular;
import ManejoDeAudios.AudioConverter;
import ManejoDeAudios.SpeechToTextIVR;

public class AudioSteps {

    public static String transcription;

    @Given("^El archivo de audio se trae automáticamente desde el celular$")
    public void givenTraerArchivoDesdeCelular() {
        String rutaCelular = "/sdcard/Recordings/Call/";
        String rutaLocal = System.getProperty("user.dir") + File.separator + "Llamadas";
        String udid = "R9WW70LL61V";
        TraerAudioDesdeCelular.desde(udid, rutaCelular, rutaLocal);
    }

    @And("^Se realiza el reconocimiento de voz sobre el archivo convertido$")
    public void cuandoSeReconoceAudio() throws Exception {

        String carpetaAudios = "C:\\IVR\\Llamadas\\Call";

        System.out.println("🎤 Iniciando transcripción con Whisper Python...");
        System.out.println("📂 Carpeta: " + carpetaAudios);

        transcription = SpeechToTextWhisperPython.transcribe(carpetaAudios);

        System.out.println("📝 Transcripción recibida desde Whisper Python:");
    }

    @And("^Visualizacion de la transcripcion$")
    public void entoncesVisualizarTranscripcion() {
        assertNotNull(transcription, "La transcripción no debe ser nula");
        System.out.println(BLUE + "=== Transcripción del audio ===" + RESET);
        System.out.println(transcription);
        System.out.println(BLUE + "===============================" + RESET);
    }

}
