package ManejoDeAudios.Whisper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SpeechToTextWhisperPython {

    private static final String PYTHON = "C:\\Users\\Pruebas\\whisper_env\\Scripts\\python.exe";
    private static final String SCRIPT = "C:\\Whisper\\transcribe_whisper.py";

    // 🔹 Ahora recibe solo la ruta de la carpeta, NO un WAV específico
    public static String transcribe(String folderPath) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                PYTHON,
                SCRIPT,
                folderPath
        );

        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder text = new StringBuilder();
        String line;

        // 🔹 Captura la salida completa del script Python
        while ((line = reader.readLine()) != null) {
            System.out.println("PY → " + line); // OPCIONAL: Debug
            text.append(line).append("\n");
        }

        int exit = process.waitFor();

        if (exit != 0) {
            throw new RuntimeException("❌ Whisper Python falló con código: " + exit +
                    "\nSalida:\n" + text);
        }

        return text.toString().trim();
    }
}
