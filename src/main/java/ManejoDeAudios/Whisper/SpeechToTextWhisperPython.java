package ManejoDeAudios.Whisper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SpeechToTextWhisperPython {

    private static final String PYTHON =
            System.getenv("WHISPER_PYTHON") != null &&
                    !System.getenv("WHISPER_PYTHON").trim().isEmpty()
                    ? System.getenv("WHISPER_PYTHON")
                    : "C:\\Users\\PARDOC\\AppData\\Local\\Programs\\Python\\Python313\\python.exe";

    private static final String SCRIPT =
            "C:\\Whisper\\transcribe_whisper.py";

    public static String transcribe(String folderPath) throws Exception {

        System.out.println("🐍 Python usado: " + PYTHON);
        System.out.println("📜 Script Whisper: " + SCRIPT);
        System.out.println("📂 Carpeta audio: " + folderPath);

        ProcessBuilder pb = new ProcessBuilder(
                PYTHON,
                SCRIPT,
                folderPath
        );

        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

        StringBuilder text = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println("PY → " + line);
            text.append(line).append("\n");
        }

        int exit = process.waitFor();

        if (exit != 0) {
            throw new RuntimeException(
                    "❌ Whisper Python falló con código: " + exit +
                            "\nSalida:\n" + text
            );
        }

        return text.toString().trim();
    }
}