Feature: Validar los flujos de WhatsApp

  @IVR_01
  Scenario Outline: Marcacion IVR
    Given Se realiza la llamada al numero *611#


    Examples:
      | saludo |  numero | valor |
  ##@externalData@./src/test/resources/data/Datos.xlsx@correo@
   |Hola   |3558   |$ 50.000|



