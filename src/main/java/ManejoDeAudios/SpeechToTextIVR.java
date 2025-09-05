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
          // Menú inicial
          "WhatsApp",
          "marca 1", "marca 2", "marca 3", "marca 4", "marca 5",
          "marca 6", "marca 7", "marca 8", "marca 9", "marca 0",

          // Opciones de líneas
          "linea prepago", "línea prepago",
          "linea postpago", "línea postpago",

          // Servicios y consultas
          "consultar tu plan", "pagar tu factura", "factura", "saldo",
          "consumos", "compra de paquetes", "otros detalles",
          "paquetes de datos", "minutos", "recargas",

          // Navegación y validación
          "número de celular", "es correcto", "corregirlo",
          "menú principal", "escuchar nuevamente", "volver atrás",
          "continuar", "repetir", "finalizar", "pagos",

          // Atención a problemas y soporte
          "soporte", "servicios móviles", "servicios fijos",
          "internet", "televisión", "telefonía",
          "visitas", "traslados", "clave wi-fi",
          "fallas técnicas", "reportar daño",

          // Teclas especiales
          "asterisco", "estrella",
          "numeral", "gatillo",

          // Más servicios
          "información general", "activar un servicio",
          "gestionar sus equipos", "cancelación",
          "queja", "recurso", "servicios especiales",
          "atención al cliente", "hablar con un asesor", "agente"
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
            .setModel("telephony") // ✅ usar modelo telephony en vez de phone_call
            .setUseEnhanced(true) // ✅ activa el modelo mejorado
            .setEnableAutomaticPunctuation(false) // ⚡ evita comas y puntos
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

    return cleanDynamicBlock(
            normalizeText(transcription.toString().trim())
    );
  }

  public static String recognizeBytes(byte[] audioBytes) throws Exception {
    SpeechContext context = SpeechContext.newBuilder()
            .addAllPhrases(Arrays.asList(IVR_KEYWORDS))
            .setBoost(20.0f)
            .build();

    RecognitionConfig config = RecognitionConfig.newBuilder()
            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
            .setLanguageCode("es-CO")
            .setSampleRateHertz(16000)
            .setModel("telephony")
            .setUseEnhanced(true)
            .addSpeechContexts(context)
            .build();

    StringBuilder transcription = new StringBuilder();

    try (SpeechClient speechClient = SpeechClient.create()) {
      ByteString bytes = ByteString.copyFrom(audioBytes);
      RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(bytes).build();

      RecognizeResponse response = speechClient.recognize(config, audio);
      for (SpeechRecognitionResult result : response.getResultsList()) {
        transcription.append(result.getAlternatives(0).getTranscript()).append(" ");
      }
    }

    return cleanDynamicBlock(
            normalizeText(transcription.toString().trim())
    );
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

  // Normaliza texto: números, acentos y elimina signos
  private static String normalizeText(String text) {
    if (text == null) return "";
    text = text.toLowerCase()
            // Números en palabra -> dígitos
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
            .replaceAll("\\bdoce\\b", "12")
            // Quitar acentos
            .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
            .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
            .replaceAll("[úùüû]", "u")
            // Quitar signos
            .replaceAll("[,.;:!?]", "")
            // Normalizar espacios
            .replaceAll("\\s+", " ").trim();
    return text;
  }

  // Elimina el bloque dinámico del IVR que siempre cambia en números
  private static String cleanDynamicBlock(String text) {
    if (text == null || text.isEmpty()) return "";

    // Expresión regular: desde "de las siguientes opciones..." hasta "marca 3"
    text = text.replaceAll(
            "de las siguientes opciones selecciona el servicio relacionado con tu consulta.*?marca 3",
            ""
    );

    // Normalizar espacios sobrantes
    return text.replaceAll("\\s+", " ").trim();
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
