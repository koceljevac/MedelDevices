package features.main.profile.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import features.main.home.presentation.screens.HomeScreen

object ProfileTab:Tab {
    @Composable
    override fun Content() {
        Navigator(ProfileScreen())
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Profile"
            val icon = rememberVectorPainter(Icons.Rounded.Face)
            return  remember {
                TabOptions(
                    index = 3u,
                    title =title,
                    icon= icon
                )
            }
        }
}