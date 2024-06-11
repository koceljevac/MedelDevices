package features.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import core.utils.validateEmail
import core.utils.validatePassword
import core.utils.validateUsername
import features.auth.data.models.UserModel
import features.auth.presentation.viewmodel.registrationViewModel.RegistrationViewModel
import features.auth.presentation.viewmodel.registrationViewModel.mvi.RegistrationEvent
import features.auth.presentation.viewmodel.registrationViewModel.mvi.RegistrationState
import org.koin.compose.koinInject

object RegistrationScreen : Screen {
    @Composable
    override fun Content() {
        registrationContent()
    }
}

@Composable
private fun registrationContent() {
    val viewModel: RegistrationViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()
    val navigator = LocalNavigator.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf<String?>(null) }
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
                value = username,
                onValueChange = {
                    username = it
                    usernameError = validateUsername(it)
                },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                isError = usernameError != null
            )
            if (usernameError != null) {
                Text(text = usernameError!!, color = MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = validateEmail(it)
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError != null
            )
            if(emailError!=null){
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
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordError != null
            )
            if (passwordError != null) {
                Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    usernameError = validateUsername(username)
                    emailError = validateEmail(email)
                    passwordError = validatePassword(password)

                    if (usernameError == null && emailError == null && passwordError == null) {
                        viewModel.onEvent(RegistrationEvent.RegisterUser(UserModel(username=username,email=email,password=password)))
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }
        }
        when(state){
            is RegistrationState.Loading ->{
                CircularProgressIndicator()
            }
            is RegistrationState.RegistrationSuccesful ->{
                navigator?.replace(LoginScreen)

            }
            is RegistrationState.Error ->{

            }
            else ->Unit
        }
    }
}
