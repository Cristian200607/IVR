Feature: Validar los flujos de IVR

  #@IVR_01
  Scenario Outline: Marcacion IVR
    Given Se realiza la llamada al numero *611#
    When Se comienza a grabar la llamda
    Examples:
      | saludo |  numero | valor |
  ##@externalData@./src/test/resources/data/Datos.xlsx@correo@
   |Hola   |3558   |$ 50.000|

  @Audio_01
  Scenario: Convertir y reconocer un archivo de audio
    Given El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio en formato OGG ubicado en Llamadas
    When Se convierte el archivo a WAV mono 16kHz
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    Then Visualizacion de la trancripcion

  @IVR_00
  Scenario: Marcación IVR y reconocimiento de audio menu principal
    Given Se realiza la llamada al numero *611
    When  Se valida el flujo menu principal
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio en formato OGG ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la trancripcion
    Then  Validar la transcripción con el texto esperado del menu principal


  @IVR_01
  Scenario: Marcación IVR y reconocimiento de audio opcion 1
    Given Se realiza la llamada al numero *611#
    And   Se marca la opcion uno y empieza a grabar
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio en formato OGG ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la trancripcion
    Then  Validar la transcripción con el texto esperado de Opcion 1

  @IVR_02
  Scenario: Marcación IVR y reconocimiento de audio opcion 2
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion dos y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio en formato OGG ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la trancripcion
    Then Validar la transcripción con el texto esperado de Opcion 2

  @IVR_03
  Scenario: Marcación IVR y reconocimiento de audio opcion 2 y 1
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion 1 en comprar servicios y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio en formato OGG ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la trancripcion
    #Then Validar la transcripción con el texto esperado de Opcion 2 depues 1

  @IVR_07
  Scenario: Marcación IVR y reconocimiento de audio opcion 3
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion tres y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio en formato OGG ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la trancripcion
    Then Validar la transcripción con el texto esperado de Opcion 3
