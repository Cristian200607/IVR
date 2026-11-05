package utils;

public class Constantes {

    public static final String RUTA_CAPTURA = "C:\\IVR\\IVR\\Capturas";
    public static final String TEXTO_ESPERADO = "Ingresa WhatsApp y ahorra tiempo";
    public static final double UMBRAL = 0.7;
    public static final String FIN_LLAMADA_GRABACION = "Fin de llamada";
    public static final String ATENCION_AL_CLIENTE = "Atencion al Cliente";

    // Celular y app usados en la automatización Vivo=10AECM1AP5000XT
    // Otro celular: definir la variable de entorno UDID (adb devices)
    public static final String UDID =
            System.getenv("UDID") != null && !System.getenv("UDID").trim().isEmpty()
                    ? System.getenv("UDID")
                    : "NVOL4H0111";
    public static final String APP_MARCADOR = "com.google.android.dialer";


}