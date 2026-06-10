package utils;

public class IVRTiempos {

    // ==============================
    // TIEMPOS DE GRABACIÓN
    // ==============================

    /** Espera antes de iniciar grabación */
    public static final int INICIO_GRABACION = 30;

    /** Duración de grabación del menú principal (~36 seg) */
    public static final int MENU_PRINCIPAL = 34500;

    /** Duración menús cortos (opciones simples) */
    public static final int MENU_CORTO = 15000;

    /** Duración menús medianos */
    public static final int MENU_MEDIANO = 25000;

    /** Duración menús largos (con PQR, roaming, etc.) */
    public static final int MENU_LARGO = 45000;

    /** Duración menús muy largos (flujos completos) */
    public static final int MENU_MUY_LARGO = 60000;

    /** Espera después de marcar una opción */
    public static final int ESPERA_ENTRE_OPCIONES = 2000;

    /** Tiempo para que ADB traiga el audio */
    public static final int ESPERA_ADB = 5000;
}
