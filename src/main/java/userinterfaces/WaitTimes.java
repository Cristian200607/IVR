package userinterfaces;

public class WaitTimes {

    /** Desde que contesta el IVR hasta que ofrece marcar 1 o 2, sin avisos. */
    public static final int SALUDO = 11000;

    /**
     * Aviso temporal que el IVR reproduce después del saludo (hoy: el de orden público, 34 s en total
     * hasta la opción 1 o 2). Cuando el IVR lo quite, dejarlo en 0.
     */
    public static final int AVISO = 23000;

    public static final int INICIO_LLAMADA = SALUDO + AVISO;
    public static final int CORTO = 5000;
    public static final int MEDIO = 10000;
    public static final int LARGO = 30000;

    private WaitTimes() {
        // Evita instanciar esta clase
    }
}
