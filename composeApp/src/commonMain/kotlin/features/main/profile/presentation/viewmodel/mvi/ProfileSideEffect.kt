package features.main.profile.presentation.viewmodel.mvi

import core.utils.ViewSideEffect

sealed class ProfileSideEffect: ViewSideEffect{
    data object ShowError: ProfileSideEffect()
}