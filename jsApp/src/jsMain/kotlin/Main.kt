import androidx.compose.runtime.Composable
import com.example.demomovie.ShareMovie
import com.example.demomovie.model.Movie
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

suspend fun main() {
    var movies: List<Movie> = ShareMovie().getMovies("upcoming")

    renderComposable(rootElementId = "root") {
        Div({ style { padding(25.px) } }) {

            H1{
                Text("Hello, The Movie DB")
            }
            H3{
                Text("Let's stream your favorite movie")
            }

            Text("$movies")
        }
    }
}

@Composable
fun Body() {
}