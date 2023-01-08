package us.xylight.multitranslate.enums

/**
 * Represents the formality options in DeepL translate.
 *
 * @param apiName
 * Represents the name of the formality in the API.
 */
enum class Formality(val apiName: String) {
    FORMAL("prefer_more"),
    NEUTRAL("default"),
    CASUAL("prefer_less")
}