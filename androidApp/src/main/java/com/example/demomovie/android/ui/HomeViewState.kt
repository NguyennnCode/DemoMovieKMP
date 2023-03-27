package com.example.demomovie.android.ui

import com.example.demomovie.model.Movie

data class HomeViewState(
    val bannerMovies: List<Movie> = emptyList(),
    val categoryMovies: List<Movie> = emptyList()
)
