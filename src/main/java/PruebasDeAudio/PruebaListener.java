package PruebasDeAudio;

import ManejoDeAudios.IvrListener;
import ManejoDeAudios.SincronizarAudio;
import net.serenitybdd.screenplay.Actor;

import static userinterfaces.IVRPage.BOTON_TEST;

public class PruebaListener {

    public static void main(String[] args) {

        Actor actor = Actor.named("Tester");
        String carpetaCelular = "/sdcard/Recordings/Call/";
        String carpetaPC = "C:\\IVR\\Llamadas\\Temp\\";
        String palabraClave = "quieres hacerlo marca uno"; // frase que queremos detectar
        int tiempoEsperaMs = 1000; // revisar cada segundo

        // Hilo 1: sincroniza el audio desde el celular al PC
        Thread hiloSincronizar = new Thread(() -> {
            SincronizarAudio.desde(carpetaCelular, carpetaPC, tiempoEsperaMs);
        });

        // Hilo 2: listener que detecta la palabra clave en los audios de la carpeta
        Thread hiloListener = new Thread(() -> {
            IvrListener listener = new IvrListener(
                    carpetaPC,
                    actor,
                    BOTON_TEST,
                    tiempoEsperaMs,
                    palabraClave
            );
            listener.run();
        });

        // Iniciar ambos hilos
        hiloSincronizar.start();
        hiloListener.start();

        try {
            hiloListener.join(); // espera a que el listener detecte la palabra
            System.out.println("Palabra clave detectada y acción ejecutada.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Opcional: detener el hilo de sincronización si quieres
        hiloSincronizar.interrupt();
    }
}