package ManejoDeAudios;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class IvrListener implements Runnable {

    private final String carpetaLocal; // Carpeta a monitorear
    private final Actor actor;
    private final Target boton;
    private final int tiempoEsperaMs;
    private final String palabraClave;

    public IvrListener(String carpetaLocal, Actor actor, Target boton, int tiempoEsperaMs, String palabraClave) {
        this.carpetaLocal = carpetaLocal;
        this.actor = actor;
        this.boton = boton;
        this.tiempoEsperaMs = tiempoEsperaMs;
        this.palabraClave = palabraClave.toLowerCase();
    }

    @Override
    public void run() {
        try {
            Map<String, Long> posiciones = new HashMap<>(); // Última posición leída por archivo

            while (true) {
                File carpeta = new File(carpetaLocal);
                File[] archivos = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));

                if (archivos != null) {
                    for (File archivo : archivos) {
                        long ultimaPos = posiciones.getOrDefault(archivo.getName(), 0L);

                        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
                            long longitudArchivo = raf.length();

                            if (longitudArchivo > ultimaPos) {
                                raf.seek(ultimaPos);
                                byte[] buffer = new byte[(int)(longitudArchivo - ultimaPos)];
                                raf.readFully(buffer);

                                String texto = SpeechToTextIVR.recognizeBytes(buffer);
                                System.out.println("Archivo: " + archivo.getName() + " -> " + texto);

                                if (texto.toLowerCase().contains(palabraClave)) {
                                    System.out.println("Palabra clave detectada en " + archivo.getName());
                                    actor.attemptsTo(Click.on(boton));
                                    return; // salir del listener
                                }

                                posiciones.put(archivo.getName(), longitudArchivo);
                            }
                        }
                    }
                }

                Thread.sleep(tiempoEsperaMs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
