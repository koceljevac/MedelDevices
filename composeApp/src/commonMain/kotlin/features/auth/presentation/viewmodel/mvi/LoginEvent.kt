package features.auth.presentation.viewmodel.mvi

import core.utils.ViewEvent
import features.auth.data.models.UserModel
import features.auth.domain.entites.UserLogin

sealed class LoginEvent : ViewEvent {
    data class LoginUser(val login: UserModel): LoginEvent()
}