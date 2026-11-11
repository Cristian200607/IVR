import os
import sys
import subprocess
import tempfile
import unicodedata
import re

from faster_whisper import WhisperModel


# ============================================================
# CONFIGURACIÓN
# ============================================================

WHISPER_MODEL = "small"


# ============================================================
# PALABRAS CLAVE PARA ORIENTAR A WHISPER
# ============================================================

KEYWORDS = [
    "whatsapp",
    "plan",
    "factura",
    "servicios",
    "menu",
    "principal",
    "marca",
    "cliente",
    "portafolio",
    "claro empresas",
    "numero",
    "atencion",
    "ventas",
    "activar",
    "servicio al cliente",
    "identificacion",
    "digito de verificacion",
    "tecla numeral",
    "numero digitado",
    "es correcto",
    "corregir",
    "productos financiados",
    "detalle de tu plan",
    "consumos",
    "soporte",
    "servicio movil",
    "peticiones",
    "quejas",
    "reclamos",
    "recursos",
    "prepago",
    "cancelacion",
    "robo",
    "perdida",
    "reconectar",
    "registrar tu equipo",
    "medios de pago",
    "correo electronico",
    "siguiente corte",
    "plan empresarial"
]


# ============================================================
# BLOQUES A ELIMINAR
# ============================================================

BLOCKS_TO_REMOVE = [
    (
        "alta congestion",
        "agradecemos"
    ),

    (
        "de las siguientes opciones",
        "marca 3"
    )
]


# ============================================================
# CORRECCIONES DE ERRORES FRECUENTES DE WHISPER
# ============================================================

TEXT_CORRECTIONS = {
    "activir": "activar",
    "activir nuestros servicios": "activar nuestros servicios",

    "ferisco": "productos financiados",

    "ediciones": "peticiones",

    "claro empresa": "claro empresas",

    "whats app": "whatsapp",
    "what sap": "whatsapp",

    "productos financiado": "productos financiados",
    "producto financiado": "productos financiados",

    "servicio movil": "servicio movil",
    "servicios movil": "servicios moviles"
}


# ============================================================
# CONVERTIR AUDIO A WAV PCM 16 KHZ
# ============================================================

def convert_to_valid_wav(input_path):

    temp_wav = tempfile.NamedTemporaryFile(
        delete=False,
        suffix=".wav"
    ).name

    cmd = [
        "ffmpeg",
        "-y",
        "-i",
        input_path,

        "-af",
        (
            "highpass=f=100,"
            "lowpass=f=3800,"
            "afftdn=nf=-25,"
            "acompressor="
            "threshold=-18dB:"
            "ratio=3:"
            "attack=20:"
            "release=250,"
            "loudnorm="
            "I=-16:"
            "TP=-1.5:"
            "LRA=11"
        ),

        "-acodec",
        "pcm_s16le",
        "-ac",
        "1",
        "-ar",
        "16000",

        temp_wav
    ]

    result = subprocess.run(
        cmd,
        stdout=subprocess.DEVNULL,
        stderr=subprocess.PIPE,
        text=True
    )

    if result.returncode != 0:

        print(
            "ERROR FFmpeg:",
            file=sys.stderr
        )

        print(
            result.stderr,
            file=sys.stderr
        )

        if os.path.exists(temp_wav):
            os.remove(temp_wav)

        raise RuntimeError(
            "FFmpeg no pudo convertir el audio."
        )

    return temp_wav


# ============================================================
# BUSCAR ARCHIVO DE AUDIO
# ============================================================

def find_audio_file(path):

    allowed_extensions = (
        ".wav",
        ".mp3",
        ".m4a",
        ".aac",
        ".ogg",
        ".amr"
    )

    for file in os.listdir(path):

        if file.lower().endswith(
            allowed_extensions
        ):

            return os.path.join(
                path,
                file
            )

    return None


# ============================================================
# NORMALIZAR TEXTO
# ============================================================

def normalize_text(text):

    if not text:
        return ""

    # Eliminar tildes
    text = unicodedata.normalize(
        "NFD",
        text
    )

    text = "".join(
        character
        for character in text
        if unicodedata.category(character) != "Mn"
    )

    # Minúsculas
    text = text.lower()

    # Eliminar caracteres especiales
    text = re.sub(
        r"[^\w\s]",
        "",
        text
    )

    # Espacios repetidos
    text = re.sub(
        r"\s+",
        " ",
        text
    )

    return text.strip()


# ============================================================
# CORREGIR ERRORES DE WHISPER
# ============================================================

def correct_transcription(text):

    if not text:
        return ""

    for wrong, correct in TEXT_CORRECTIONS.items():

        text = re.sub(
            rf"\b{re.escape(wrong)}\b",
            correct,
            text,
            flags=re.IGNORECASE
        )

    # Limpieza adicional

    text = re.sub(
        r"\s+",
        " ",
        text
    )

    return text.strip()


# ============================================================
# CONVERTIR PALABRAS NUMÉRICAS
# Una cifra suelta seguida de otra se deja separada (línea, NIT, indicativo: "3 2 0 3"),
# y los números compuestos se arman completos ("cuarenta y cuatro mil..." -> 44864).
# ============================================================

# "un" y "una" no se convierten: casi siempre son artículos ("un mensaje", "una opción").
UNIDADES = {"cero": 0, "uno": 1, "dos": 2, "tres": 3, "cuatro": 4, "cinco": 5,
            "seis": 6, "siete": 7, "ocho": 8, "nueve": 9}
ESPECIALES = {"diez": 10, "once": 11, "doce": 12, "trece": 13, "catorce": 14, "quince": 15,
              "dieciseis": 16, "diecisiete": 17, "dieciocho": 18, "diecinueve": 19, "veinte": 20,
              "veintiun": 21, "veintiuno": 21, "veintiuna": 21, "veintidos": 22, "veintitres": 23,
              "veinticuatro": 24, "veinticinco": 25, "veintiseis": 26, "veintisiete": 27,
              "veintiocho": 28, "veintinueve": 29}
DECENAS = {"treinta": 30, "cuarenta": 40, "cincuenta": 50, "sesenta": 60, "setenta": 70,
           "ochenta": 80, "noventa": 90}
CENTENAS = {"cien": 100, "ciento": 100, "doscientos": 200, "doscientas": 200, "trescientos": 300,
            "trescientas": 300, "cuatrocientos": 400, "cuatrocientas": 400, "quinientos": 500,
            "quinientas": 500, "seiscientos": 600, "seiscientas": 600, "setecientos": 700,
            "setecientas": 700, "ochocientos": 800, "ochocientas": 800, "novecientos": 900,
            "novecientas": 900}


def _es_numero(p):
    return p in UNIDADES or p in ESPECIALES or p in DECENAS or p in CENTENAS or p == "mil"


def _leer_menor_que_mil(pal, i):
    """Lee un grupo menor que mil (p. ej. 'ochocientos sesenta y cuatro'). Devuelve (valor, siguiente) o (None, i)."""
    inicio, valor = i, 0
    if i < len(pal) and pal[i] in CENTENAS:
        valor += CENTENAS[pal[i]]; i += 1
    if i < len(pal) and pal[i] in DECENAS:
        valor += DECENAS[pal[i]]; i += 1
        # 'cuarenta y cuatro': la 'y' solo une una decena con una unidad
        if i + 1 < len(pal) and pal[i] == "y" and pal[i + 1] in UNIDADES and UNIDADES[pal[i + 1]] > 0:
            valor += UNIDADES[pal[i + 1]]; i += 2
    elif i < len(pal) and pal[i] in ESPECIALES:
        valor += ESPECIALES[pal[i]]; i += 1
    elif i < len(pal) and pal[i] in UNIDADES and (i == inicio or UNIDADES[pal[i]] > 0):
        valor += UNIDADES[pal[i]]; i += 1
    return (valor, i) if i > inicio else (None, inicio)


def _leer_numero(pal, i):
    """Lee un número completo con 'mil'. Una cifra suelta seguida de otra cifra suelta NO se suma:
    'tres dos cero' son tres dígitos dictados (línea, NIT, indicativo), no 5."""
    valor, j = _leer_menor_que_mil(pal, i)
    if valor is None and j + 1 < len(pal) and pal[j] in ("un", "una") and pal[j + 1] == "mil":
        valor, j = 1, j + 1  # "un mil" -> 1000
    if j < len(pal) and pal[j] == "mil":
        valor = (valor if valor is not None else 1) * 1000
        j += 1
        resto, k = _leer_menor_que_mil(pal, j)
        if resto is not None:
            valor += resto; j = k
    return (valor, j) if valor is not None else (None, i)


def palabras_a_numeros(texto):
    pal = texto.split()
    salida, i = [], 0
    while i < len(pal):
        un_mil = pal[i] in ("un", "una") and i + 1 < len(pal) and pal[i + 1] == "mil"
        if not _es_numero(pal[i]) and not un_mil:
            salida.append(pal[i]); i += 1; continue
        valor, j = _leer_numero(pal, i)
        if valor is None:
            salida.append(pal[i]); i += 1; continue
        salida.append(str(valor)); i = j
    return " ".join(salida)


def preparar_cifras(texto):
    """Antes de quitar signos: '44.864' -> '44864' (miles) y '60.0' -> '60 punto 0' (decimal)."""
    texto = re.sub(r"\$\s*", " ", texto)
    # Miles con punto o coma ("44.864" o "44,864"): son pesos, no decimales
    texto = re.sub(r"\b(\d{1,3})(?:[.,]\d{3})+\b", lambda m: re.sub(r"[.,]", "", m.group(0)), texto)
    texto = re.sub(r"\b(\d+)[.,](\d+)\b", r"\1 punto \2", texto)
    return texto


# ============================================================
# ELIMINAR BLOQUES DE TEXTO
# ============================================================

def remove_blocks(text, blocks):

    if not text:
        return ""

    text = text.strip()

    for start, end in blocks:

        pattern = (
            rf"{re.escape(start)}.*?"
            rf"{re.escape(end)}"
        )

        text = re.sub(
            pattern,
            "",
            text,
            count=1,
            flags=re.IGNORECASE
        )

    text = re.sub(
        r"\s+",
        " ",
        text
    )

    return text.strip()


# ============================================================
# CORTAR TODO LO ANTERIOR AL ANCHOR
# ============================================================

def cut_from_anchor(text, anchor):

    if not text:
        return ""

    position = text.lower().find(
        anchor.lower()
    )

    if position != -1:

        return text[position:].strip()

    return text.strip()


# ============================================================
# LIMPIEZA DE REPETICIONES
# ============================================================

def remove_repeated_words(text):

    if not text:
        return ""

    words = text.split()

    result = []

    previous_word = None
    repetition_count = 0

    for word in words:

        normalized_word = word.lower()

        if normalized_word == previous_word:

            repetition_count += 1

            # Permitir máximo dos repeticiones
            if repetition_count >= 2:
                continue

        else:

            repetition_count = 0

        result.append(word)

        previous_word = normalized_word

    return " ".join(result)


# ============================================================
# PROCESAR TRANSCRIPCIÓN
# ============================================================

def process_transcription(text):

    # 1. Normalizar (antes, cifras: "44.864" -> "44864", "60.0" -> "60 punto 0")
    text = normalize_text(preparar_cifras(text))

    # 2. Correcciones
    text = correct_transcription(text)

    # 3. Convertir números
    text = palabras_a_numeros(text)

    # 4. Correcciones posteriores
    text = correct_transcription(text)

    # 5. Eliminar bloques
    text = remove_blocks(
        text,
        BLOCKS_TO_REMOVE
    )

    # 6. Eliminar repeticiones accidentales
    text = remove_repeated_words(text)

    # 7. Cortar desde la frase principal
    text = cut_from_anchor(
        text,
        "bienvenido a nuestra linea de atencion"
    )

    # 8. Limpieza final
    text = re.sub(
        r"\s+",
        " ",
        text
    ).strip()

    return text


# ============================================================
# MAIN
# ============================================================

if __name__ == "__main__":

    wav_file = None

    try:

        # ----------------------------------------------------
        # VALIDAR ARGUMENTOS
        # ----------------------------------------------------

        if len(sys.argv) < 2:

            print(
                "No se recibió la carpeta del audio.",
                file=sys.stderr
            )

            sys.exit(1)

        folder = sys.argv[1]

        if not os.path.exists(folder):

            print(
                f"La carpeta no existe: {folder}",
                file=sys.stderr
            )

            sys.exit(1)

        # ----------------------------------------------------
        # BUSCAR AUDIO
        # ----------------------------------------------------

        audio_file = find_audio_file(
            folder
        )

        if not audio_file:

            print(
                "No se encontró ningún archivo de audio.",
                file=sys.stderr
            )

            sys.exit(1)

        # ----------------------------------------------------
        # CONVERTIR AUDIO
        # ----------------------------------------------------

        wav_file = convert_to_valid_wav(
            audio_file
        )

        # ----------------------------------------------------
        # CARGAR MODELO
        # ----------------------------------------------------

        model = WhisperModel(
            WHISPER_MODEL,
            device="cpu",
            compute_type="int8"
        )

        # ----------------------------------------------------
        # TRANSCRIBIR
        # ----------------------------------------------------

        segments, info = model.transcribe(

            wav_file,

            language="es",

            initial_prompt=" ".join(
                KEYWORDS
            ),

            temperature=0.0,

            vad_filter=True
        )

        # ----------------------------------------------------
        # UNIR SEGMENTOS
        # ----------------------------------------------------

        text = " ".join(
            segment.text
            for segment in segments
        )

        # ----------------------------------------------------
        # PROCESAR TRANSCRIPCIÓN
        # ----------------------------------------------------

        transcription = process_transcription(
            text
        )

        # ----------------------------------------------------
        # ÚNICA SALIDA POR STDOUT
        # ----------------------------------------------------
        #
        # IMPORTANTE:
        # No colocar ningún otro print()
        # en stdout.
        #
        # Java recibirá únicamente este texto.
        # ----------------------------------------------------

        sys.stdout.write(
            transcription
        )

        sys.stdout.flush()

    except Exception as error:

        # Los errores se envían por STDERR.
        # Así no contaminan la transcripción.

        print(
            f"ERROR: {error}",
            file=sys.stderr
        )

        sys.exit(1)

    finally:

        # ----------------------------------------------------
        # ELIMINAR WAV TEMPORAL
        # ----------------------------------------------------

        if wav_file and os.path.exists(
            wav_file
        ):

            try:

                os.remove(
                    wav_file
                )

            except Exception as error:

                print(
                    f"ERROR eliminando WAV temporal: {error}",
                    file=sys.stderr
                )