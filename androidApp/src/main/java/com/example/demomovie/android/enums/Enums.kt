package com.example.demomovie.android.enums

class Enums {

}

enum class Category(val category: String) {
    TOP_RATED(category = "top_rated"),
    NOW_PLAYING(category = "now_playing"),
    LATEST(category = "latest"),
    POPULAR(category = "popular"),
    UP_COMING(category = "upcoming")
}