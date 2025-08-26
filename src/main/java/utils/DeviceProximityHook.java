package utils;

import cucumber.api.java.Before;

public class DeviceProximityHook {

    /**
     * Antes de cualquier escenario, desactiva el sensor de proximidad si está activado
     */
    @Before
    public void disableProximityIfNeeded() {
        try {
            // Leer el valor actual del sensor
            Process readProcess = Runtime.getRuntime().exec(
                    "adb shell settings get system proximity_on");
            readProcess.waitFor();

            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(readProcess.getInputStream()));
            String line = reader.readLine();
            if (line == null) line = "1"; // por defecto activado

            if (line.trim().equals("1")) { // activado, hay que desactivar
                Runtime.getRuntime().exec("adb shell settings put system proximity_on 0");
                System.out.println("✅ Sensor de proximidad desactivado para la prueba");
            } else {
                System.out.println("ℹ️ Sensor de proximidad ya estaba desactivado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
