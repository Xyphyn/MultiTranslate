package us.xylight.multitranslate.data

import us.xylight.multitranslate.enums.Language

data class Translation(val translatedText: String, val detectedLanguage: Language?)
