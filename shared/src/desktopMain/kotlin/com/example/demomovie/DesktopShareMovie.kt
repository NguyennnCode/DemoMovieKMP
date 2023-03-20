package com.example.demomovie

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    engine {
        pipelining = true
        threadsCount = 4
    }
}

