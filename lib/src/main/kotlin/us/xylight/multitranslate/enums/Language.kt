package us.xylight.multitranslate.enums

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
    HEBREW("he", Provider.DEEPL, Provider.LINGVA),
    ARABIC("ar", Provider.DEEPL),
    DANISH("da"),
    INDONESIAN("id"),
    IRISH("ga", Provider.DEEPL),
    HUNGARIAN("hu"),
    KOREAN("ko", Provider.DEEPL),
    GREEK("el"),
    SWEDISH("sv"),
    UKRAINIAN("uk"),
    HINDI("hi", Provider.DEEPL),
    JAPANESE("ja"),
    CHINESE("zh"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it"),
    TURKISH("tr"),
    CZECH("cs"),
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