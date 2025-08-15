package ManejoDeAudios;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.*;
import com.google.protobuf.ByteString;

import javax.sound.sampled.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SpeechToTextIVR {

  private static final String[] IVR_KEYWORDS = {
          "WhatsApp", "marca 1", "marca 2", "marca 3", "marca 4", "marca 9",
          "linea prepago", "Claro", "computadores", "televisores", "celulares", "linea postpago"
  };

  private static final String BUCKET_NAME = "ivr_bucket_claro"; // bucket GCS

  public static String recognize(String audioFilePath) throws Exception {
    Path path = Paths.get(audioFilePath);

    // Calcular duración del audio
    double durationSeconds = getAudioDurationSeconds(path);
    System.out.printf("Duración del audio: %.2f segundos%n", durationSeconds);

    SpeechContext context = SpeechContext.newBuilder()
            .addAllPhrases(Arrays.asList(IVR_KEYWORDS))
            .setBoost(20.0f)
            .build();

    RecognitionConfig config = RecognitionConfig.newBuilder()
            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
            .setLanguageCode("es-CO")
            .setSampleRateHertz(16000)
            .setModel("phone_call")
            .addSpeechContexts(context)
            .build();

    StringBuilder transcription = new StringBuilder();

    try (SpeechClient speechClient = SpeechClient.create()) {

      if (durationSeconds <= 60) {
        // Procesamiento síncrono con inline audio
        byte[] data = Files.readAllBytes(path);
        ByteString audioBytes = ByteString.copyFrom(data);
        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(audioBytes)
                .build();

        RecognizeResponse response = speechClient.recognize(config, audio);
        for (SpeechRecognitionResult result : response.getResultsList()) {
          transcription.append(result.getAlternatives(0).getTranscript()).append(" ");
        }

      } else {
        // Procesamiento asíncrono con audio en GCS
        String gcsUri = uploadToGCS(audioFilePath, BUCKET_NAME);
        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setUri(gcsUri)
                .build();

        OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                speechClient.longRunningRecognizeAsync(config, audio);

        LongRunningRecognizeResponse longResponse = response.get(300, TimeUnit.SECONDS);
        for (SpeechRecognitionResult result : longResponse.getResultsList()) {
          transcription.append(result.getAlternatives(0).getTranscript()).append(" ");
        }
      }
    }

    return normalizeNumbers(transcription.toString().trim());
  }

  // Subir audio a Google Cloud Storage
  private static String uploadToGCS(String localFilePath, String bucketName) throws Exception {
    Storage storage = StorageOptions.getDefaultInstance().getService();
    Path path = Paths.get(localFilePath);
    String fileName = path.getFileName().toString();
    BlobId blobId = BlobId.of(bucketName, fileName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, Files.readAllBytes(path));
    System.out.println("Archivo subido a GCS: " + fileName);
    return "gs://" + bucketName + "/" + fileName;
  }

  // Calcula duración del WAV
  private static double getAudioDurationSeconds(Path path) throws Exception {
    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path.toFile())) {
      AudioFormat format = audioInputStream.getFormat();
      long frames = audioInputStream.getFrameLength();
      return (frames + 0.0) / format.getFrameRate();
    }
  }

  private static String normalizeNumbers(String text) {
    if (text == null) return "";
    return text.toLowerCase()
            .replaceAll("\\bcero\\b", "0")
            .replaceAll("\\buno\\b", "1")
            .replaceAll("\\bdos\\b", "2")
            .replaceAll("\\btres\\b", "3")
            .replaceAll("\\bcuatro\\b", "4")
            .replaceAll("\\bcinco\\b", "5")
            .replaceAll("\\bseis\\b", "6")
            .replaceAll("\\bsiete\\b", "7")
            .replaceAll("\\bocho\\b", "8")
            .replaceAll("\\bnueve\\b", "9")
            .replaceAll("\\bdiez\\b", "10")
            .replaceAll("\\bonce\\b", "11")
            .replaceAll("\\bdoce\\b", "12");
  }

  public static void main(String[] args) {
    try {
      Path folder = Paths.get("LlamadasConvertidas/");
      DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.wav");

      for (Path file : stream) {
        try {
          System.out.println("\n--- Transcribiendo: " + file.getFileName() + " ---");
          String texto = recognize(file.toString());
          System.out.println("=== Transcripción completa ===");
          System.out.println(texto);
          System.out.println("===============================");
        } catch (Exception e) {
          System.err.println("Error procesando " + file.getFileName() + ": " + e.getMessage());
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
