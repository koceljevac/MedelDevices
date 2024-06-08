import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme
import features.main.MainScreen
import org.koin.compose.KoinContext


@Composable
fun App() {
    KoinContext {
        initialScreen()
    }
}


@Composable
fun initialScreen() {
    AppTheme {
        Navigator(MainScreen())
    }
}
