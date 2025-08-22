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
        String textoEsperado = "ingresa whatsapp y ahorra tiempo podrá consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  estás en la línea de ventas claro si tu consulta sobre un servicio diferente por favor finalizar la llamada vuelve a marcar ingresa por la opción 3 del menú principal para servicios hogar marca 1 comprar un plan pospago móvil marca 2 comprar celulares televisores computadores marca 3 adquirir servicios fijos o móviles para que empresa y pasarte a claro con tu mismo número marca 4 marca 9 si deseas escuchar de nuevo la información  bienvenido a la línea de ventas de claro hogar recuerda y tu consulta sobre servicio al cliente y no para comprar servicios por favor finaliza esta llamada vuelve a marcar ingresa por la opción 3 del menú principal dime lo que deseas adquirir por ejemplo quiero comprar internet hogar";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 2 depues 4$")
    public void validarTranscripcionOpcion2y4() {
        String textoEsperado = "ingresa whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3  estás en la línea de ventas claro si tu consulta sobre un servicio diferente por favor finaliza esta llamada vuelve a marcar ingresa por la opción 3 del menú principal para servicios hogar marca 1 comprar un plan pospago móvil marca 2 comprar celulares televisores computadores marca 3 adquirir servicios fijos o móviles para que empresa y pasarte a claro con tu mismo número marca 4 marca 9 si deseas escuchar de nuevo la información  aquí podrás adquirir servicios exclusivamente para tu negocio o empresa si tu requerimientos diferente te invitamos a llamar nuevamente y escuché con atención nuestro menú principal";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 3$")
    public void validarTranscripcionOpcion3() {
        String textoEsperado = "ingresa a whatsapp y ahorra tiempo podrás consultar tu plan pagar tu factura y mucho más quieres hacer marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menú principal marca 3 de las siguientes opciones seleccione el servicio relacionado con tu consulta línea prepago terminada en 0 5 6 marca 1 línea postpago terminada en 1 0 2 marca 2 línea postpago terminada en 8 4 0 marca 3 quieres consultar otro servicio marca 4 para repetir este menú marca 9";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Validar Menu principal despues de digitar la linea$")
    public void validarTranscripcionValidarMenuPrincipalDespuesDeValidarLaLinea() {
        String textoEsperado = "ingresa whatsapp y ahorra tiempo podra consultar tu plan pagar tu factura y mucho mas quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menu principal marca 3 de las siguientes opciones seleccione servicio relacionado con tu consulta linea prepago terminada en 056 marca 1 linea postpago terminada en 182 marca 2 linea postpago terminada en 102 marca 3 quieres consultar otro servicio marca 4 para repetir este menu marca 9 por favor ingresa el numero de celular que vas a consultar tu numero de celular es 321 326 8056 es correcto marca 1 eres corregirlo marca 2 apreciado usuario por actividades de mejoramiento de nuestra red se puede presentar variacion en la calidad de su servicio de voz o datos le informamos que en nuestra area tecnica se encuentra trabajando para normalizar el servicio a la mayor brevedad muchas gracias hemos notado que llamaste recientemente por saldo prepago si ese es el motivo de tu llamada marca 1 o 9 para ir al menu principal tienes dudas con tu factura pagos saldo consumos compra de paquetes y otros detalles marca asterisco si necesita soporte de tus servicios moviles fijos gestionar visitas traslados o tu clave wi-fi marca numeral para informacion general o activar sus servicios marca 1 para gestionar sus equipos marca 2 si necesitas una queja recurso o solicitar la cancelacion de tus servicios marca 3 si quieres conocer servicios especiales para ti marca 4 escuchar nuevamente las opciones marca 9";
        validarTranscripcion(textoEsperado);
    }

    @Then("^Validar la transcripción con el texto esperado de Opcion 3 despues 4$")
    public void validarTranscripcionOpcion3y24() {
        String textoEsperado = "ingresa whatsapp y ahorra tiempo podras consultar tu plan pagar tu factura y mucho mas quieres hacerlo marca 1 si quieres comprar alguno de nuestros servicios claro marca 2 para continuar con nuestro menu principal marca 3 de las siguientes opciones selecciona el servicio relacionado con su consulta linea prepago terminada en 056 marca 1 linea postpago terminada en 102 marca 2 linea postpago terminada en 840 marca 3 quieres consultar otro servicio marca 4 para repetir este menu marca 9 por favor ingresa el numero de celular que vas a consultar tu numero de celular es 321 326 8056 es correcto marca 1 eres corregirlo marca 2 hemos notado que llamaste recientemente por saldo prepago si ese es el motivo de tu llamada marca 1 o 9 para ir al menu principal tienes dudas con tu factura para 2 saldo consumos compra de paquetes y otros detalles marca asterisco si necesita soporte de tus servicios moviles fijos gestionar visitas traslados o tu clave wi-fi marca numeral para informacion general o activar un servicio marca 1 para gestionar sus equipos marca 2 si necesitas a dejar una queja recurso o solicitar la cancelacion de tus servicios marca 3 si quieres conocer servicios especiales para ti marca 4 escuchar nuevamente las opciones marca 9";
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
