package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstadoPrueba {
  public static boolean fallo = false;
  public static String pasoFallido = "";
  public static long inicio = 0L;
  public static long fin = 0L;

  // Campos para el reporte
  public static String transcripcion = "";
  public static String textoEsperado = "";
  public static String linea = "";  // 👉 número de celular usado en la prueba
  public static String horaInicio = "";     // 👉 hora en que empezó la prueba

  public static void reset() {
    fallo = false;
    pasoFallido = "";
    inicio = System.currentTimeMillis();
    fin = 0L;

    transcripcion = "";
    textoEsperado = "";
    linea = "";

    // Guarda automáticamente la hora actual al iniciar la prueba
    horaInicio = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
  }
}
