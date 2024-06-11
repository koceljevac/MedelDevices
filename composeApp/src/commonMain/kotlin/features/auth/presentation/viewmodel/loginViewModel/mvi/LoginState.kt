package features.auth.presentation.viewmodel.loginViewModel.mvi

import core.utils.ViewState
import features.auth.data.models.JWTokenDto

sealed class LoginState : ViewState {
    data object Initial: LoginState()
    data object Loading: LoginState()

    data class LoginSuccesful(val jwTokenDto: JWTokenDto): LoginState()

    data class Error(val message: String?): LoginState()
}