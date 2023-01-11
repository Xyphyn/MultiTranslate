package us.xylight.multitranslate.translators

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import us.xylight.multitranslate.MultiTranslate
import us.xylight.multitranslate.enums.Provider
import us.xylight.multitranslate.data.*
import us.xylight.multitranslate.enums.Feature
import us.xylight.multitranslate.enums.Formality
import us.xylight.multitranslate.enums.Language

class LibreTranslator(private val key: String = "", private val url: String) : Translator {
    override val features: List<Feature> = listOf(Feature.DETECT_LANGUAGE)

    override suspend fun translate(
        text: String,
        language: Language,
        from: Language?,
        formality: Formality
    ): Translation {
        validateProviderSupport(language, Provider.LIBRE_TRANSLATE)

        val jsonPayload = MultiTranslate.json.encodeToJsonElement(
            LibreTranslationRequest(text, from?.code ?: "auto", language.code, apiKey = key)
        )

        val request = Request.Builder()
            .method("POST", jsonPayload.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("User-Agent", "MultiTranslate/1.0.0")
            .url(url)
            .build()

        MultiTranslate.httpClient.newCall(request).execute().use { response ->
            val resText = response.body?.string()!!
            val translation = kotlin.runCatching { MultiTranslate.json.decodeFromString<LibreTranslationResponse>(resText) }.getOrElse { throw TranslationException("There was an error decoding the response. Did you forget an API key?") }

            return Translation(
                translation.translatedText,
                Language.languageFromCode(translation.detectedLanguage!!.language)
            )
        }
    }

    override suspend fun detectLanguage(text: String): Language {
        val jsonPayload = MultiTranslate.json.encodeToJsonElement(
        LibreTranslationRequest(text, "auto", Language.ENGLISH.code, apiKey = key)
    )

        val request = Request.Builder()
            .method("POST", jsonPayload.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("User-Agent", "MultiTranslate/1.0.0")
            .url(url)
            .build()

        MultiTranslate.httpClient.newCall(request).execute().use { response ->
            val resText = response.body?.string()!!
            val translation = kotlin.runCatching { MultiTranslate.json.decodeFromString<LibreTranslationResponse>(resText) }.getOrElse { throw TranslationException("There was an error decoding the response. Did you forget an API key?") }

            return Language.languageFromCode(translation.detectedLanguage!!.language)!!
        }
    }
}