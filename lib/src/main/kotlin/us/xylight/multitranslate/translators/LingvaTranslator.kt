package us.xylight.multitranslate.translators

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import us.xylight.multitranslate.MultiTranslate
import us.xylight.multitranslate.Provider
import us.xylight.multitranslate.data.*
import us.xylight.multitranslate.enums.Feature
import us.xylight.multitranslate.enums.Formality
import us.xylight.multitranslate.enums.Language

class LingvaTranslator(private val url: String) : Translator {
    override val features: List<Feature> = listOf(Feature.LANGUAGE_AUTODETCTION)

    override suspend fun translate(
        text: String,
        language: Language,
        from: Language?,
        formality: Formality
    ): Translation {
        validateProviderSupport(language, Provider.LINGVA)

        val request = Request.Builder()
            .addHeader("User-Agent", "MultiTranslate/1.0.0")
            .url("$url/api/v1/${from?.code ?: "auto"}/${language.code}/$text")
            .build()

        MultiTranslate.httpClient.newCall(request).execute().use { response ->
            val resText = response.body?.string() ?: throw TranslationException("Translation response is invalid.")

            val translation = MultiTranslate.json.decodeFromString<LingvaResponse>(resText)

            response.body?.close()

            return Translation(
                translation.translation,
                translation.info.detectedSource?.let { Language.languageFromCode(it) }
            )
        }
    }

    override suspend fun detectLanguage(text: String): Language {
        validateProviderSupport(Feature.DETECT_LANGUAGE, Provider.LINGVA)

        return Language.ENGLISH
    }
}