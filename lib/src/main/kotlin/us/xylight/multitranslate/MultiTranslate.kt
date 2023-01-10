package us.xylight.multitranslate

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object MultiTranslate {
    val httpClient: OkHttpClient = OkHttpClient.Builder().build()
    val json: Json = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
    }
}