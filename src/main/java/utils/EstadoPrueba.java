package utils;

public class EstadoPrueba {
  public static boolean fallo = false;
  public static String pasoFallido = "";
  public static long inicio = 0L;
  public static long fin = 0L;

  // Campos para el reporte
  public static String transcripcion = "";
  public static String textoEsperado = "";

  public static void reset() {
    fallo = false;
    pasoFallido = "";
    inicio = System.currentTimeMillis();
    fin = 0L;

    transcripcion = "";
    textoEsperado = "";
  }
}
