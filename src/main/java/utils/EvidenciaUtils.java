package utils;

import hooks.ReportHooks;

public class EvidenciaUtils {

    private static int contadorPasos = 1;

    /**
     * Tiempo gastado en capturas tomadas en medio de la llamada que WaitFor todavía no ha
     * descontado. Así la captura de "se marcó la opción" no corre los tiempos calibrados del IVR.
     */
    private static long tiempoPorDescontarMs = 0;

    public static void registrarCaptura(String paso) {
        String pasoNumerado = contadorPasos++ + ". " + paso;
        ReportHooks.registrarPaso(pasoNumerado);
        CapturaDePantallaMovil.tomarCapturaPantalla(pasoNumerado);
    }

    /** Igual que registrarCaptura, pero el siguiente WaitFor descuenta lo que tardó la captura. */
    public static void registrarCapturaSinAlterarTiempos(String paso) {
        long inicio = System.currentTimeMillis();
        registrarCaptura(paso);
        tiempoPorDescontarMs += System.currentTimeMillis() - inicio;
    }

    /** Devuelve y pone en cero el tiempo pendiente por descontar. */
    public static long tomarTiempoPorDescontar() {
        long tiempo = tiempoPorDescontarMs;
        tiempoPorDescontarMs = 0;
        return tiempo;
    }

    public static void reiniciarContador() {
        contadorPasos = 1;
        tiempoPorDescontarMs = 0;
    }
}
