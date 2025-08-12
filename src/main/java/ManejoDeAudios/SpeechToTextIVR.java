package ManejoDeAudios;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpeechToTextIVR {

  /**
   * Reconoce el texto contenido en un archivo de audio WAV mono 16kHz usando Google Cloud Speech-to-Text.
   *
   * @param audioFilePath Ruta al archivo WAV
   * @return Texto transcrito del audio
   * @throws Exception en caso de error
   */
  public static String recognize(String audioFilePath) throws Exception {
    Path path = Paths.get(audioFilePath);
    byte[] data = Files.readAllBytes(path);
    ByteString audioBytes = ByteString.copyFrom(data);

    RecognitionConfig config = RecognitionConfig.newBuilder()
            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
            .setLanguageCode("es-ES")
            .setSampleRateHertz(16000)
            .build();

    RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

    StringBuilder transcription = new StringBuilder();

    try (SpeechClient speechClient = SpeechClient.create()) {
      RecognizeResponse response = speechClient.recognize(config, audio);
      for (SpeechRecognitionResult result : response.getResultsList()) {
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
        transcription.append(alternative.getTranscript()).append(" ");
        System.out.printf("Transcripción parcial: %s%n", alternative.getTranscript());
      }
    }
    return transcription.toString().trim();
  }

  public static void main(String[] args) {
    try {
      String ruta = "LlamadasConvertidas/WhatsApp Audio 2025-08-08 at 2.07.56 PM.wav";
      String texto = recognize(ruta);
      System.out.println("Transcripción completa:");
      System.out.println(texto);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
