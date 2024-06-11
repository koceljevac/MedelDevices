package features.main.profile.presentation.viewmodel.mvi

import core.utils.ViewState

sealed class ProfileState:ViewState{
    data object Initial: ProfileState()
    data object Loading:ProfileState()

    data object LogoutSuccesful:ProfileState()

    data class LogoutUnsuccessful(val message: String): ProfileState()


}