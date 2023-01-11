package us.xylight.multitranslate.enums

/**
 * An enum to represent translation providers.
 */
enum class Provider(val keyRequired: Boolean) {
    DEEPL(true),
    LIBRE_TRANSLATE(false),
    LINGVA(false)
}