package us.xylight.multitranslate.data

import kotlinx.serialization.Serializable

@Serializable
data class LingvaResponse(
    val translation: String,
    val info: TranslationInfo
)

@Serializable
data class TranslationInfo(
    val detectedSource: String?
)