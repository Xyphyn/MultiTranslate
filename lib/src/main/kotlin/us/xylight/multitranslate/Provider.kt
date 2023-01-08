package us.xylight.multitranslate

/**
 * @constructor Provider
 * An enum to represent translation providers.
 */
enum class Provider(val keyRequired: Boolean) {
    DEEPL(true),
    LIBRE_TRANSLATE(false)
}