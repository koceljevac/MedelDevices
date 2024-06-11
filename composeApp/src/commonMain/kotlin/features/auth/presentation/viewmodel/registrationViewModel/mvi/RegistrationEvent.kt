package features.auth.presentation.viewmodel.registrationViewModel.mvi

import core.utils.ViewEvent
import features.auth.data.models.UserModel

sealed class RegistrationEvent : ViewEvent {
    data class RegisterUser(val register: UserModel): RegistrationEvent()
}