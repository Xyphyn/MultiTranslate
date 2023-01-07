package us.xylight.multitranslate.enums

import us.xylight.multitranslate.Provider

/**
 * Used to represent languages.
 * @param code
 * The language code. Used when calling the translation providers.
 * @param unsupportedProviders
 * Translation providers that do not support the given language.
 */
enum class Language(val code: String, vararg val unsupportedProviders: Provider) {
    ENGLISH("en"),
    SPANISH("es"),
    HEBREW("he", Provider.DEEPL),
    JAPANESE("ja"),
    CHINESE("zh"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it"),
    RUSSIAN("ru");

    companion object {
        /**
         * Returns a Language enum from a country code.
         * @return The Language which country code matches the enum.
         */
        fun languageFromCode(code: String): Language? {
            return Language.values().find { it.code.lowercase() == code.lowercase() }
        }
    }
}