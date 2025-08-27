Feature: Validar los flujos de IVR

  #@IVR_01
  Scenario Outline: Marcacion IVR
    Given Se realiza la llamada al numero *611#
    When Se comienza a grabar la llamada
    Examples:
      | saludo |  numero | valor |
  ##@externalData@./src/test/resources/data/Datos.xlsx@correo@
   |Hola   |3558   |$ 50.000|

  @Audio_01
  Scenario: Convertir y reconocer un archivo de audio
    Given El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    When Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    Then Visualizacion de la transcripcion

  @IVR_00
  Scenario: Marcación IVR y reconocimiento de audio menu principal
    Given Se realiza la llamada al numero *611
    When  Se valida el flujo menu principal
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz si es necesario
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    #Then  Validar la transcripción con el texto esperado del menu principal


  @IVR_01
  Scenario: Marcación IVR y reconocimiento de audio opcion 1
    Given Se realiza la llamada al numero *611#
    And   Se marca la opcion uno y empieza a grabar
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz si es necesario
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    #Then  Validar la transcripción con el texto esperado de Opcion 1

  @IVR_02
  Scenario: Marcación IVR y reconocimiento de audio opcion 2
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion dos y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 2

  @IVR_03
  Scenario: Marcación IVR y reconocimiento de audio opcion 2 y 1
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion 1 en comprar servicios y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 2 depues 1

  @IVR_06
  Scenario: Marcación IVR y reconocimiento de audio opcion 2 y 4
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion 4 en comprar servicios y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 2 depues 4

  @IVR_07
  Scenario: Marcación IVR y reconocimiento de audio opcion 3
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion tres y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3

  @IVR_08
  Scenario: Marcación IVR y Validar Menu Principal Despues de consultar linea
    Given Se realiza la llamada al numero *611#
    And Se valida el menu principal despues de digitar la linea a consultar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Validar Menu principal despues de digitar la linea

  @IVR_09
  Scenario: Marcación IVR y Consulta de saldo por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca la opcion cuatro en Consultar Lineas y empieza a grabar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_10
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca numeral opcion 1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_11
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca numeral opcion 2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_12
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca numeral opcion 3
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_13
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca numeral opcion 4
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_14
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Se marca numeral opcion informacion general
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_15
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_16
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_17
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_18
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_19
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_20
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op3
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_21
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op4
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_22
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op5
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_23
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op5, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_24
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op5, Op3
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_25
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Op6
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_26
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Elegidos Movil Familia
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_27
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_28
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay Master Card
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_29
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay Master Card, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_30
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Op2, valida metodos de pago
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_31
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Op2, valida metodos de pago
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_32
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Op4, Paquetes Activos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_40
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_41
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_42
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Op3
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_43
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Op4
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_44
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_45
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op1, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_46
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_47
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op2, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_48
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op2, Op2, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_49
  Scenario: Marcación IVR y Soporte de tus servicios por menu principal
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Op2, Op3
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4