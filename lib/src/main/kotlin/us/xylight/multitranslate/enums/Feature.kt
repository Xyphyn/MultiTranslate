package us.xylight.multitranslate.enums

import us.xylight.multitranslate.Provider

enum class Feature(vararg val supportedProviders: Provider) {
    DETECT_LANGUAGE(Provider.DEEPL),
    FORMALITY(Provider.DEEPL)
}