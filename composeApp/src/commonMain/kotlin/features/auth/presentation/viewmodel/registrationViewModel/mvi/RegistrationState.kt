package features.auth.presentation.viewmodel.registrationViewModel.mvi

import core.utils.ViewState

sealed class RegistrationState : ViewState {
    data object Initial: RegistrationState()
    data object Loading: RegistrationState()

    data object RegistrationSuccesful : RegistrationState()

    data class Error(val message: String?): RegistrationState()
}