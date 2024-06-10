import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme
import features.auth.presentation.screens.LoginScreen
import features.auth.presentation.viewmodel.LoginUserViewModel
import features.main.MainScreen
import org.koin.compose.KoinContext
import org.koin.compose.koinInject


@Composable
fun App() {
    KoinContext {
        val viewModel: LoginUserViewModel = koinInject()
        var initialScreen by remember { mutableStateOf<Screen?>(null) }

        LaunchedEffect(Unit) {
            viewModel.fetchToken { token ->
                initialScreen = if (token.isNullOrEmpty()) {
                    LoginScreen
                } else {
                    MainScreen()
                }
            }
        }

        if (initialScreen != null) {
            AppTheme {
                Navigator(initialScreen!!)
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}