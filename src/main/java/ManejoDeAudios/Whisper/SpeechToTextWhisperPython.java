package ManejoDeAudios.Whisper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class SpeechToTextWhisperPython {

    private static final boolean ES_WINDOWS =
            System.getProperty("os.name").toLowerCase().contains("win");

    private static final String CARPETA_WHISPER_MAC =
            Paths.get(System.getProperty("user.home"), "Whisper").toString();

    // Variables de entorno WHISPER_PYTHON y WHISPER_SCRIPT mandan; si no están, se usa la ruta del sistema operativo.
    private static final String PYTHON = variableOValorPorDefecto("WHISPER_PYTHON",
            ES_WINDOWS
                    ? "C:\\Users\\PARDOC\\AppData\\Local\\Programs\\Python\\Python313\\python.exe"
                    : pythonMac());

    // El script versionado en el proyecto (whisper/transcribe_whisper.py) es el que se usa en Mac y en Windows;
    // las rutas de afuera solo quedan de respaldo si no se encuentra.
    private static final File SCRIPT_DEL_PROYECTO =
            Paths.get(System.getProperty("user.dir"), "whisper", "transcribe_whisper.py").toFile();

    private static final String SCRIPT = variableOValorPorDefecto("WHISPER_SCRIPT",
            SCRIPT_DEL_PROYECTO.isFile()
                    ? SCRIPT_DEL_PROYECTO.getPath()
                    : ES_WINDOWS
                            ? "C:\\Whisper\\transcribe_whisper.py"
                            : Paths.get(CARPETA_WHISPER_MAC, "transcribe_whisper.py").toString());

    private static String variableOValorPorDefecto(String variable, String porDefecto) {
        String valor = System.getenv(variable);
        return valor != null && !valor.trim().isEmpty() ? valor : porDefecto;
    }

    // En Mac/Linux usa el entorno virtual de ~/Whisper si existe; si no, el python3 del sistema.
    private static String pythonMac() {
        File venv = Paths.get(CARPETA_WHISPER_MAC, ".venv", "bin", "python").toFile();
        return venv.canExecute() ? venv.getPath() : "python3";
    }

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