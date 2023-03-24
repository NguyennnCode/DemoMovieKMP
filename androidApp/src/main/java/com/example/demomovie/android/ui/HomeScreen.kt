package com.example.demomovie.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demomovie.ShareMovie
import com.example.demomovie.android.enums.Category
import com.example.demomovie.android.theme.primaryColor
import com.example.demomovie.android.ui.component.SearchField
import com.example.demomovie.android.ui.component.TopBannerField
import com.example.demomovie.android.ui.component.UserField
import com.example.demomovie.android.ui.component.category.CategoriesField
import com.example.demomovie.model.Movie
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(modifier: Modifier, onGoDetail: (Movie) -> Unit) {
    var movies: List<Movie>
    runBlocking {
        movies = ShareMovie().getMovies(Category.UP_COMING.category)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues.calculateTopPadding())
        ) {
            UserField(modifier = Modifier.padding(horizontal = 24.dp))

            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                SearchField(modifier = Modifier)
            }

            Spacer(modifier = Modifier.height(24.dp))
            TopBannerField(modifier = Modifier, movies = movies) {
                onGoDetail(it)
            }

            Spacer(modifier = Modifier.height(24.dp))
            CategoriesField(modifier = Modifier.fillMaxWidth()) {
                onGoDetail(it)
            }
        }
    }
}