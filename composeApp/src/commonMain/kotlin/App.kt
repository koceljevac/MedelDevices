
import androidx.compose.runtime.*

import cafe.adriel.voyager.navigator.Navigator
import com.example.compose.AppTheme
import features.auth.presentation.screens.LoginScreen
import features.auth.presentation.screens.RegistrationScreen
import features.main.MainScreen

import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(screen = MainScreen())
    }
}