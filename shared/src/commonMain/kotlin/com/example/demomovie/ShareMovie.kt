package com.example.demomovie

import com.example.demomovie.model.Movie
import com.example.demomovie.model.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

const val BaseUrl: String = "api.themoviedb.org"
const val ApiKey: String = "09d7a831c79372626b4eca6e2f8487d1"

private val client = httpClient()

class ShareMovie {
    suspend fun getMovies(category: String, page: Int = 1): List<Movie> {
        val response = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BaseUrl
                path("3/movie/$category")
                parameters.append("api_key", ApiKey)
                parameters.append("language", "en-US")
                parameters.append("page", "$page")
            }
        }
        return response.body<MovieResponse>().results
    }
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient