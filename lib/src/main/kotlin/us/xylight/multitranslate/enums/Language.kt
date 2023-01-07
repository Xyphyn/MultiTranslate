package us.xylight.multitranslate.enums

import us.xylight.multitranslate.Provider

/**
 * Used to represent languages.
 * @param code
 * The language code. Used when calling the translation providers.
 * @param unsupportedProviders
 * Translation providers that do not support the given language.
 */
enum class Language(code: String, vararg unsupportedProviders: Provider) {
    ENGLISH("en"),
    SPANISH("es"),
    HEBREW("ir", Provider.LIBRE_TRANSLATE)
}