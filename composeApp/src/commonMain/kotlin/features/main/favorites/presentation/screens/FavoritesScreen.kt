package features.main.favorites.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object FavoritesScreen: Tab {
    @Composable
    override fun Content() {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Text("Favorites Screen")
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Favorites"
            val icon  = rememberVectorPainter(Icons.Rounded.Favorite)
            return remember {
                TabOptions(
                    index = 1u,
                    title=title,
                    icon =icon
                )
            }
        }
}