package us.xylight.multitranslate.translators

import us.xylight.multitranslate.Provider
import us.xylight.multitranslate.data.Translation
import us.xylight.multitranslate.enums.Language

interface Translator {
    class Builder {
        private var provider: Provider? = null

        fun provider(provider: Provider) = apply { this.provider = provider }

        fun build(): Translator {
            if (provider == null) throw IllegalArgumentException("provider is a required argument.")

            return when (provider) {
                Provider.LIBRE_TRANSLATE -> LibreTranslateTranslator()
                Provider.DEEPL -> DeepLTranslator()
                else -> throw IllegalArgumentException("provider is a required argument.")
            }
        }
    }

    fun translate(text: String, language: Language, from: Language): Translation = Translation("I don't know how you managed to use the regular translator class.")
}