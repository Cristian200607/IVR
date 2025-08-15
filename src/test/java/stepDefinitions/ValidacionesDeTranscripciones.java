package stepDefinitions;

import cucumber.api.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import static stepDefinitions.AudioSteps.transcription;

import ManejoDeAudios.EliminarAudioEnCelular;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class ValidacionesDeTranscripciones {

    private static final String RUTA_ARCHIVO_CELULAR = "/sdcard/Recordings/Call/";
    private static final double UMBRAL_COINCIDENCIA = 90.0; // Porcentaje mínimo aceptable

    @Then("^Validar la transcripción con el texto esperado del menu principal$")
    public void validarTranscripcionMenuPrincipal() {
        String textoEsperado = "ingresa a whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 1$")
    public void validarTranscripcionOpcion1() {
        String textoEsperado = "ingresa a whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  tu consulta será atendida por medio de whatsapp";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 2$")
    public void validarTranscripcionOpcion2() {
        String textoEsperado = "ingresa a whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  estás en la línea de ventas claro si tú consultas sobre un servicio diferente por favor finaliza esta llamada vuelve a marcar ingresa por la opción 3 del menú principal para servicios hogar marca 1 comprar un plan postpago móvil marca 2 comprar celulares televisores computadores marca 3 adquirir servicios fijos o móviles para tu empresa y pasarte a claro con tu mismo número marca 4 marca 9 si deseas escuchar de nuevo la información";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 2 depues 1$")
    public void validarTranscripcionOpcion2y1() {
        String textoEsperado = "ingresa whatsapp y ahorra tiempo podrá consultar tu plan para que para que durar mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  estás en la línea de ventas claro si tu consulta sobre un servicio diferente por favor finaliza esta llamada vuelve a marcar ingresa por la opción 3 del menú principal para servicios hogar marca 1 comprar un plan pospago móvil marcar alonso comprar celulares televisores computadores marca 3 adquirir servicios fijos o móviles para que empresa y pasarte a claro con tu mismo número marca 4 marca 9 si deseas escuchar de nuevo la información bienvenido a la línea de ventas de claro hogar y tu consulta sobre servicio al cliente y no para comprar servicios por favor vuelve a marcar ingresa por la opción 3 del menú principal dime lo que deseas adquirir por ejemplo quiero comprar internet hogar";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 3$")
    public void validarTranscripcionOpcion3() {
        String textoEsperado = "ingresa a whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3 de las siguientes opciones seleccione el servicio relacionado con tu consulta línea postpago terminada en 1 8 8 marca 1 línea postpago terminada en 1 1 3 marca 2 línea postpago terminada en 4 4 3 marca 3 quieres consultar otro servicio marca 4 para repetir este menú marca 9";
        validarTranscripcion(textoEsperado);
    }

    // Método central de validación
    private void validarTranscripcion(String textoEsperado) {
        try {
            String esperadoNormalizado = normalizar(textoEsperado);
            String transcripcionNormalizada = normalizar(transcription);

            double porcentaje = calcularPorcentajeCoincidencia(transcripcionNormalizada, esperadoNormalizado);

            assertTrue(
                    porcentaje >= UMBRAL_COINCIDENCIA,
                    () -> "La transcripción no coincide lo suficiente.\n" +
                            "Esperado: " + esperadoNormalizado + "\n" +
                            "Transcripción: " + transcripcionNormalizada + "\n" +
                            "Coincidencia: " + porcentaje + "%"
            );
            System.out.println("✅ Coincidencia aceptable (" + porcentaje + "%)");
            System.out.println("✅ Texto Esperado");
            System.out.println(esperadoNormalizado);

        } finally {
            EliminarAudioEnCelular.ejecutar(RUTA_ARCHIVO_CELULAR);
        }
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        texto = texto.toLowerCase()
                // Números en palabra -> dígitos
                .replaceAll("\\bcero\\b", "0")
                .replaceAll("\\buno\\b", "1").replaceAll("\\bdos\\b", "2")
                .replaceAll("\\btres\\b", "3").replaceAll("\\bcuatro\\b", "4")
                .replaceAll("\\bcinco\\b", "5").replaceAll("\\bseis\\b", "6")
                .replaceAll("\\bsiete\\b", "7").replaceAll("\\bocho\\b", "8")
                .replaceAll("\\bnueve\\b", "9").replaceAll("\\bdiez\\b", "10")
                .replaceAll("\\bonce\\b", "11").replaceAll("\\bdoce\\b", "12")
                // Números ya escritos (para asegurar)
                .replaceAll("\\b(1|2|3|4|5|6|7|8|9|10|11|12)\\b", "$1")
                // Quitar acentos
                .replaceAll("[áàäâ]", "a").replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i").replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                // Quitar signos y espacios extra
                .replaceAll("[,.;:!?]", "").replaceAll("\\s+", " ").trim();
        return texto;
    }

    private double calcularPorcentajeCoincidencia(String t1, String t2) {
        LevenshteinDistance distancia = new LevenshteinDistance();
        int dist = distancia.apply(t1, t2);
        int maxLen = Math.max(t1.length(), t2.length());
        return Math.round((1 - ((double) dist / maxLen)) * 10000.0) / 100.0; // 2 decimales
    }
}
