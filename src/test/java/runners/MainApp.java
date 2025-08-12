package runners;

import ManejoDeAudios.AudioConverter;
import ManejoDeAudios.SpeechToTextIVR;

public class MainApp {
    public static void main(String[] args) {
        try {
            String inputPath = "C:/IVR/Llamadas/audio.ogg";
            String wavPath = "C:/IVR/Llamadas/audio_convertido.wav";

            System.out.println("Convirtiendo audio...");
            AudioConverter.convertToWav(inputPath, wavPath);
            System.out.println("Conversión completada.");

            System.out.println("Reconociendo audio...");
            SpeechToTextIVR.recognize(wavPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

