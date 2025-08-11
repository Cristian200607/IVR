Feature: Validar los flujos de IVR

  @IVR_01
  Scenario Outline: Marcacion IVR
    Given Se realiza la llamada al numero *611#


    Examples:
      | saludo |  numero | valor |
  ##@externalData@./src/test/resources/data/Datos.xlsx@correo@
   |Hola   |3558   |$ 50.000|




  @Audio_01
  Scenario: Convertir y reconocer un archivo de audio
    Given El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio en formato OGG ubicado en Llamadas
    When Se convierte el archivo a WAV mono 16kHz
    And Se realiza el reconocimiento de voz sobre el archivo convertido en la carpeta "LlamadasConvertidas"
    Then Visualizacion de la trancripcion

