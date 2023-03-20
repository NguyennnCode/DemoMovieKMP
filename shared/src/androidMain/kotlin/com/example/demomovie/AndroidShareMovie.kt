package com.example.demomovie

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("KTOR", message)
            }
        }
        level = LogLevel.ALL // logs everything
    }

    engine {
        pipelining = true
        threadsCount = 4
        config {
            retryOnConnectionFailure(true)
            connectTimeout(0, TimeUnit.SECONDS)
        }
    }
}