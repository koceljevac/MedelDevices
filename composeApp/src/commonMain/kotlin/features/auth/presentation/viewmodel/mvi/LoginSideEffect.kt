package features.auth.presentation.viewmodel.mvi

import core.utils.ViewSideEffect

sealed class LoginSideEffect : ViewSideEffect{
    data object ShowError: LoginSideEffect()
}