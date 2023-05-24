package com.example.demomovie.di

import com.example.demomovie.httpClient
import com.example.demomovie.service.MovieApi
import com.example.demomovie.service.MovieApiIml
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


fun shareModule() = module {
    loadKoinModules(networkModule())
}

private fun networkModule() = module {
    single { httpClient() }
    factory<MovieApi> { MovieApiIml(get()) }
}