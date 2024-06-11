package features.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.utils.validateEmail
import core.utils.validatePassword
import features.auth.data.models.UserModel
import features.auth.presentation.viewmodel.LoginUserViewModel
import features.auth.presentation.viewmodel.loginViewModel.mvi.LoginEvent
import features.auth.presentation.viewmodel.loginViewModel.mvi.LoginState
import features.main.MainScreen
import org.koin.compose.koinInject

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        LoginContent()
    }
}


@Composable
private fun LoginContent() {
    val viewModel: LoginUserViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = validateEmail(it)
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
            )
            if (emailError != null) {
                Text(text = emailError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = validatePassword(it)
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (passwordError != null) {
                Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    emailError = validateEmail(email)
                    passwordError = validatePassword(password)
                    if (emailError == null && passwordError == null) {
                        viewModel.onEvent(LoginEvent.LoginUser(UserModel(email=email, password = password)))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don't have an account? ")
                ClickableText(
                    text = AnnotatedString("Register"),
                    onClick = {
                        navigator.push(RegistrationScreen)
                    }
                )
            }

            when (state) {
                is LoginState.Loading -> {
                    // Show loading indicator
                }
                is LoginState.LoginSuccesful -> {
                    val token = (state as LoginState.LoginSuccesful).jwTokenDto
                    //println("JWT TOKEN $token")
                    navigator.parent?.replace(MainScreen())
                }
                is LoginState.Error -> {
                    println("Usao je u gresku")
                    val error = (state as LoginState.Error).message
                    error?.let { it1 -> Text(text = it1, color = MaterialTheme.colorScheme.error) }
                }
                else -> Unit
            }
        }
    }
}
