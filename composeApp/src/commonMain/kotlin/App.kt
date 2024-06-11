import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme
import core.ui.root.RootScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        AppTheme {
            Navigator(RootScreen())
        }
    }
}
