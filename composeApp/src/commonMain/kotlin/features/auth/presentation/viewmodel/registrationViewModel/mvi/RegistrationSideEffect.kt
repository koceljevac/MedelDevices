package features.auth.presentation.viewmodel.registrationViewModel.mvi

import core.utils.ViewSideEffect

sealed class RegistrationSideEffect : ViewSideEffect {
    data object ShowError: RegistrationSideEffect()
}