package stepDefinitions;

import ManejoDeAudios.Whisper.SpeechToTextWhisperPython;
import cucumber.api.java.en.*;
import java.io.File;

import static coloresTerminal.colores.BLUE;
import static coloresTerminal.colores.RESET;
import static org.junit.jupiter.api.Assertions.*;
import static userinterfaces.IVRPage.BTN_COLGAR;

import ManejoDeAudios.EliminarAudioEnCelular;
import ManejoDeAudios.LimpiarYRespaldarAudio;
import ManejoDeAudios.TraerAudioDesdeCelular;
import ManejoDeAudios.AudioConverter;
import ManejoDeAudios.SpeechToTextIVR;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.actions.Click;

public class AudioSteps {

    public static String transcription;

    @Given("^El archivo de audio se trae automáticamente desde el celular$")
    public void givenTraerArchivoDesdeCelular() {

        String rutaCelular = "/storage/emulated/0/Download/";

        String rutaLocal = System.getProperty("user.dir")
                + File.separator
                + "Llamadas"
                + File.separator
                + "Call";

        String udid = "10AECM1AP5000XT";

        TraerAudioDesdeCelular.desde(
                udid,
                rutaCelular,
                rutaLocal
        );
    }

    @And("^Se realiza el reconocimiento de voz sobre el archivo convertido$")
    public void cuandoSeReconoceAudio() throws Exception {

        String rutaLocal = System.getProperty("user.dir") + File.separator + "Llamadas";
        String carpetaAudios = rutaLocal + File.separator + "Call";

        System.out.println("Iniciando transcripción con Whisper Python...");
        System.out.println("Carpeta: " + carpetaAudios);

        transcription = SpeechToTextWhisperPython.transcribe(carpetaAudios);

        System.out.println("Transcripción recibida desde Whisper Python:");
    }

    @And("^Visualizacion de la transcripcion$")
    public void entoncesVisualizarTranscripcion() {
        assertNotNull(transcription, "La transcripción no debe ser nula");
        System.out.println(BLUE + "=== Transcripción del audio ===" + RESET);
        System.out.println(transcription);
        System.out.println(BLUE + "===============================" + RESET);
    }

    private static final String RUTA_ARCHIVO_CELULAR = "/storage/emulated/0/Download/";
    private static final String UDID = "10AECM1AP5000XT";
    private static final double UMBRAL_COINCIDENCIA = 90.0;

    @And("^Se eliminan y respaldan los audios del celular$")
    public void eliminarYRespaldarAudios() {

        EliminarAudioEnCelular.ejecutar(UDID, RUTA_ARCHIVO_CELULAR);

        LimpiarYRespaldarAudio.ejecutar(
                "Llamadas/Call",
                "Llamadas/BackupsAudio"
        );

        System.out.println("Audios eliminados y respaldados correctamente");
    }

}