package ManejoDeAudios;

import java.io.File;

public class AudioRecorder {

    public static void main(String[] args) {
        try {
            String ffmpegPath = "ffmpeg";
            String audioDevice = "Línea de entrada (Realtek(R) Audio)";
            String outputFolderPath = "C:\\IVR\\Llamadas\\Call";

            File outputFolder = new File(outputFolderPath);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            String fileName = String.format("grabacion_%tF_%tH-%tM-%tS.wav",
                    new java.util.Date(), new java.util.Date(), new java.util.Date(), new java.util.Date());
            String outputPath = outputFolderPath + "\\" + fileName;

            // Comando FFmpeg para grabar en mono a 16 kHz
            String command = String.format("\"%s\" -f dshow -i audio=\"%s\" -ac 1 -ar 16000 \"%s\"",
                    ffmpegPath, audioDevice, outputPath);

            Process process = Runtime.getRuntime().exec(command);
            System.out.println("Grabando en: " + outputPath);
            System.out.println("Presiona Ctrl+C para detener la grabación...");

            process.waitFor();
            System.out.println("Grabación finalizada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
