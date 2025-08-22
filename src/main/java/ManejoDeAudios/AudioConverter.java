package ManejoDeAudios;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class AudioConverter {

  /**
   * Convierte un archivo de audio a WAV mono 16kHz usando ffmpeg.
   *
   * @param inputFile Ruta del archivo original (ogg, mp3, etc)
   * @param outputFile Ruta donde se guardará el archivo WAV convertido
   * @throws Exception en caso de error en la ejecución
   */
  public static void convertToWav(String inputFile, String outputFile) throws Exception {
    // Ruta completa a ffmpeg.exe (ajustada con tu ruta real)
    String ffmpegPath = "C:\\Herramientas\\ffmpeg-7.1.1-full_build\\bin\\ffmpeg.exe";

    // Verificar si el archivo existe
    File ffmpegFile = new File(ffmpegPath);
    if (!ffmpegFile.exists()) {
      throw new RuntimeException("No se encontró ffmpeg en: " + ffmpegPath);
    }

    // Comando para convertir audio con ffmpeg usando la ruta completa
    String command = String.format("\"%s\" -y -i \"%s\" -ac 1 -ar 16000 \"%s\"",
            ffmpegPath, inputFile, outputFile);

    System.out.println("Ejecutando comando: " + command);

    Process process = Runtime.getRuntime().exec(command);

    // Leer la salida y errores para evitar bloqueos
    try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
         BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
      String s;
      while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
      }
      while ((s = stdError.readLine()) != null) {
        System.err.println(s);
      }
    }

    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new RuntimeException("Error al convertir audio con ffmpeg, código: " + exitCode);
    }
  }

  public static void main(String[] args) {
    try {
      System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));

      String inputPath = "Llamadas/audio.ogg";       // 👈 pon el archivo real
      String outputPath = "LlamadasConvertidas/audio.wav";

      System.out.println("Convirtiendo audio...");
      convertToWav(inputPath, outputPath);
      System.out.println("Conversión completada: " + outputPath);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
