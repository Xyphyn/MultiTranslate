package us.xylight.multitranslate.translators

import us.xylight.multitranslate.Provider
import us.xylight.multitranslate.data.Translation
import us.xylight.multitranslate.enums.Feature
import us.xylight.multitranslate.enums.Formality
import us.xylight.multitranslate.enums.Language

interface Translator {
    class Builder {
        private var provider: Provider? = null
        private var key: String? = null
        private var url: String = "https://api-free.deepl.com/v2/translate"

        /**
         * The provider to use for translation.
         */
        fun provider(provider: Provider) = apply { this.provider = provider }

        /**
         * The key to use for the translation provider. It may be called an API key, or an authentication token.
         */
        fun key(key: String) = apply { this.key = key }

        /**
         * The URL to use for translation. Only for DeepL.
         */
        fun url(url: String) = apply { this.url = url }

        fun build(): Translator {
            if (provider == null) throw IllegalArgumentException("provider is a required argument.")

            if (key == null && provider!!.keyRequired) throw IllegalArgumentException("The provided provider requires the argument key.")

            return when (provider) {
                Provider.DEEPL -> DeepLTranslator(key!!, url)
                else -> throw IllegalArgumentException("provider is a required argument.")
            }
        }
    }

    fun validateProviderSupport(language: Language, provider: Provider) {
        if (language.unsupportedProviders.contains(provider)) {
            throw IllegalArgumentException("Provider $provider does not support language $language")
        }
    }

    fun validateProviderSupport(feature: Feature, provider: Provider) {
        if (!feature.supportedProviders.contains(provider)) {
            throw IllegalArgumentException("Provider $provider does not support feature $feature")
        }
    }

    suspend fun translate(text: String, language: Language, from: Language?, formality: Formality = Formality.NEUTRAL): Translation

    suspend fun detectLanguage(text: String): Language
}