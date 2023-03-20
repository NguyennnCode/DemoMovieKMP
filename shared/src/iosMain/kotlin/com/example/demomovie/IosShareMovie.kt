package com.example.demomovie

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    engine {
        configureRequest {
            pipelining = true
            threadsCount = 4
            setAllowsCellularAccess(true)
        }
    }
}