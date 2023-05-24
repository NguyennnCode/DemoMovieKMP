package com.example.demomovie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.demomovie.android.ui.HomeScreen
import com.example.shared_ui.ui.ShareMovieDetail
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(modifier = Modifier) {
                                navController.navigate("detail/${it.title}${it.posterPath}/${it.overview}")
                            }
                        }
                        composable(
                            "detail/{title}/{posterPath}/{overView}",
                            arguments = listOf(
                                navArgument("title") {
                                    type = NavType.StringType
                                },
                                navArgument("posterPath") { // Notice over here
                                    type = NavType.StringType
                                },
                                navArgument("overView") { // Notice over here
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            it.arguments?.let { bundle ->
                                ShareMovieDetail(Modifier,
                                    title = bundle.getString("title"),
                                    posterPath = bundle.getString("posterPath"),
                                    overView = bundle.getString("overView"),
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

