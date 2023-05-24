package com.example.demomovie.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demomovie.android.theme.primaryColor
import com.example.demomovie.android.ui.component.SearchField
import com.example.demomovie.android.ui.component.TopBannerField
import com.example.demomovie.android.ui.component.UserField
import com.example.demomovie.android.ui.component.category.CategoriesField
import com.example.demomovie.model.Movie
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    onGoDetail: (Movie) -> Unit
) {
    val state by homeViewModel.state.collectAsState()

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
            TopBannerField(modifier = Modifier, movies = state.bannerMovies) {
                onGoDetail(it)
            }

            Spacer(modifier = Modifier.height(24.dp))
            CategoriesField(modifier = Modifier.fillMaxWidth(), onChangeCategory = {
                homeViewModel.getCategoryMovies(it)
            }, categoryMovies = state.categoryMovies ) {
                onGoDetail(it)
            }
        }
    }
}