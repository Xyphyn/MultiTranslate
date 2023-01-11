package us.xylight.multitranslate.translators

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import us.xylight.multitranslate.MultiTranslate
import us.xylight.multitranslate.enums.Provider
import us.xylight.multitranslate.data.DeepLTranslationRequest
import us.xylight.multitranslate.data.DeepLTranslationResponse
import us.xylight.multitranslate.data.Translation
import us.xylight.multitranslate.data.TranslationException
import us.xylight.multitranslate.enums.Feature
import us.xylight.multitranslate.enums.Formality
import us.xylight.multitranslate.enums.Language

// Please forgive me for what I am about to code.
class DeepLTranslator(private val key: String, private val url: String = "https://api-free.deepl.com/v2/translate") : Translator {
    override val features: List<Feature> = listOf(Feature.DETECT_LANGUAGE, Feature.FORMALITY, Feature.LANGUAGE_AUTODETCTION)

    override suspend fun translate(text: String, language: Language, from: Language?, formality: Formality): Translation {
        validateProviderSupport(language, Provider.DEEPL)

        val jsonPayload = MultiTranslate.json.encodeToJsonElement(
            DeepLTranslationRequest(listOf(text), from?.code, language.code, formality.apiName)
        )

        val request = Request.Builder()
            .method("POST", jsonPayload.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("Authorization", key)
            .addHeader("User-Agent", "MultiTranslate/1.0.0")
            .url(url)
            .build()

        MultiTranslate.httpClient.newCall(request).execute().use { response ->
            val resText = response.body?.string() ?: throw TranslationException("Translation response is invalid.")
            val translation = MultiTranslate.json.decodeFromString<DeepLTranslationResponse>(resText)

            response.body?.close()

            return Translation(
                translation.translations[0].translatedText,
                Language.languageFromCode(translation.translations[0].detectedSourceLanguage)
            )
        }
    }

    override suspend fun detectLanguage(text: String): Language {
        validateProviderSupport(Feature.DETECT_LANGUAGE, Provider.DEEPL)

        val jsonPayload = MultiTranslate.json.encodeToJsonElement(
            DeepLTranslationRequest(listOf(text), null, Language.ENGLISH.code, Formality.NEUTRAL.apiName)
        )

        val request = Request.Builder()
            .method("POST", jsonPayload.toString().toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("Authorization", key)
            .addHeader("User-Agent", "MultiTranslate/1.0.0")
            .url(url)
            .build()

        MultiTranslate.httpClient.newCall(request).execute().use { response ->
            val resText = response.body?.string() ?: throw TranslationException("Translation response is invalid.")
            val translation = MultiTranslate.json.decodeFromString<DeepLTranslationResponse>(resText)

            return Language.languageFromCode(translation.translations[0].detectedSourceLanguage)!!
        }
    }
}