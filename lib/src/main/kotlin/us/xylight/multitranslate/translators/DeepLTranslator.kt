package us.xylight.multitranslate.translators

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import us.xylight.multitranslate.MultiTranslate
import us.xylight.multitranslate.Provider
import us.xylight.multitranslate.data.DeepLTranslationRequest
import us.xylight.multitranslate.data.DeepLTranslationResponse
import us.xylight.multitranslate.data.Translation
import us.xylight.multitranslate.data.TranslationException
import us.xylight.multitranslate.enums.Language

// Please forgive me for what I am about to code.
class DeepLTranslator(private val key: String, private val url: String) : Translator {
    override suspend fun translate(text: String, language: Language, from: Language?): Translation {
        validateProviderSupport(language, Provider.DEEPL)

        val jsonPayload = MultiTranslate.json.encodeToJsonElement(
            DeepLTranslationRequest(listOf(text), from?.code, language.code)
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
}