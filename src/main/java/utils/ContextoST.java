package utils;

import cucumber.api.Scenario;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ContextoST {

  private static final String CARPETA = "target/st-context";
  private static final int MAX_TRANSCRIPCION = 1500;

  private static final Map<String, Object> DATOS = new LinkedHashMap<>();

  private ContextoST() {}

  public static synchronized void reiniciar() {
    DATOS.clear();
  }

  public static synchronized void registrar(String campo, Object valor) {
    if (campo == null || valor == null) {
      return;
    }
    DATOS.put(campo, valor);
  }

  public static synchronized void registrarEscenario(Scenario scenario, String transcripcion) {
    try {
      JSONObject json = new JSONObject();
      json.put("escenario", scenario == null ? JSONObject.NULL : scenario.getName());
      json.put("tags", new JSONArray(
          scenario == null ? new ArrayList<String>() : new ArrayList<>(scenario.getSourceTagNames())));
      for (Map.Entry<String, Object> dato : DATOS.entrySet()) {
        json.put(dato.getKey(), dato.getValue());
      }
      if (transcripcion != null && !transcripcion.trim().isEmpty()) {
        String texto = transcripcion.trim();
        json.put("transcripcion",
            texto.length() > MAX_TRANSCRIPCION ? texto.substring(0, MAX_TRANSCRIPCION) + "…" : texto);
      }
      json.put("resultado", scenario != null && scenario.isFailed() ? "FAILED" : "PASSED");
      json.put("registradoEn", LocalDateTime.now().toString());

      Path carpeta = Paths.get(CARPETA);
      Files.createDirectories(carpeta);
      Files.write(carpeta.resolve(UUID.randomUUID() + ".json"),
          json.toString(2).getBytes(StandardCharsets.UTF_8));
      System.out.println("[ContextoST] Escenario registrado: " + json.optString("escenario"));
    } catch (Exception e) {
      System.err.println("[ContextoST] No se pudo registrar el escenario: " + e);
    }
  }
}
