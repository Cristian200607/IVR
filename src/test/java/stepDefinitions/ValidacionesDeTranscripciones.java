package stepDefinitions;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import static stepDefinitions.AudioSteps.transcription;
import ManejoDeAudios.EliminarAudioEnCelular;

public class ValidacionesDeTranscripciones {

    @Then("Validar la transcripción con el texto esperado del menu principal")
    public void validarTranscripcionMenuPrincipal() {
        String rutaArchivoCelular = "/sdcard/Recordings/Call/";

        try {
            String textoEsperado = "ingresa Whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca uno si quieres comprar alguno de nuestros servicios claro marca dos para continuar con nuestro menú principal marca tres";

            // Normalizamos antes de comparar
            String esperadoNormalizado = normalizarNumeros(textoEsperado);
            String transcripcionNormalizada = normalizarNumeros(transcription);

            assertTrue(
                    transcripcionNormalizada != null && transcripcionNormalizada.contains(esperadoNormalizado),
                    "La transcripción no contiene el texto esperado.\nEsperado: " + esperadoNormalizado + "\nTranscripción: " + transcripcionNormalizada
            );

        } finally {
            // Esto se ejecuta siempre, pase o no la validación
            EliminarAudioEnCelular.ejecutar(rutaArchivoCelular);
        }
    }

    @Then("Validar la transcripción con el texto esperado de Opcion 1")
    public void validarTranscripcionOpcion1() {
        String rutaArchivoCelular = "/sdcard/Recordings/Call/";

        try {
            String textoEsperado = "ingresa Whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  tu consulta será atendida por medio de WhatsApp";

            // Normalizamos antes de comparar
            String esperadoNormalizado = normalizarNumeros(textoEsperado);
            String transcripcionNormalizada = normalizarNumeros(transcription);

            assertTrue(
                    transcripcionNormalizada != null && transcripcionNormalizada.contains(esperadoNormalizado),
                    "La transcripción no contiene el texto esperado.\nEsperado: " + esperadoNormalizado + "\nTranscripción: " + transcripcionNormalizada
            );

        } finally {
            // Esto se ejecuta siempre, pase o no la validación
            EliminarAudioEnCelular.ejecutar(rutaArchivoCelular);
        }
    }

    @Then("Validar la transcripción con el texto esperado de Opcion 2")
    public void validarTranscripcionOpcion2() {
        String rutaArchivoCelular = "/sdcard/Recordings/Call/";

        try {
            String textoEsperado = "";

            // Normalizamos antes de comparar
            String esperadoNormalizado = normalizarNumeros(textoEsperado);
            String transcripcionNormalizada = normalizarNumeros(transcription);

            assertTrue(
                    transcripcionNormalizada != null && transcripcionNormalizada.contains(esperadoNormalizado),
                    "La transcripción no contiene el texto esperado.\nEsperado: " + esperadoNormalizado + "\nTranscripción: " + transcripcionNormalizada
            );

        } finally {
            // Esto se ejecuta siempre, pase o no la validación
            EliminarAudioEnCelular.ejecutar(rutaArchivoCelular);
        }
    }

    /**
     * Convierte las palabras uno/dos/tres o 1/2/3 al mismo formato (números).
     */
    private String normalizarNumeros(String texto) {
        if (texto == null) return null;

        return texto.toLowerCase()
                .replaceAll("\\buno\\b", "1")
                .replaceAll("\\bdos\\b", "2")
                .replaceAll("\\btres\\b", "3")
                .replaceAll("\\b1\\b", "1")
                .replaceAll("\\b2\\b", "2")
                .replaceAll("\\b3\\b", "3");
    }

}
