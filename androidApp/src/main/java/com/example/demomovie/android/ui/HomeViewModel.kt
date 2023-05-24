package com.example.demomovie.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demomovie.model.Movie
import com.example.demomovie.service.MovieApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val movieApi: MovieApi): ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getBannerMovies()
            getCategoryMovies("top_rated")
        }
    }

    private fun getBannerMovies(){
        var movies: List<Movie>
        viewModelScope.launch {
            movies = movieApi.getMovies("upcoming")
            _state.update {
                it.copy(bannerMovies = movies)
            }
        }
    }
    fun getCategoryMovies(category: String){
        var movies: List<Movie>
        viewModelScope.launch {
            movies = movieApi.getMovies(category)
            _state.update {
                it.copy(categoryMovies = movies)
            }
        }
    }
}