package features.main.profile.presentation.viewmodel.mvi

import core.utils.ViewEvent

sealed class ProfileEvent: ViewEvent {
    data object LogoutUser : ProfileEvent()
}
