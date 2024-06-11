package features.auth.presentation.viewmodel.loginViewModel.mvi

import core.utils.ViewEvent
import features.auth.data.models.UserModel

sealed class LoginEvent : ViewEvent {
    data class LoginUser(val login: UserModel): LoginEvent()
}