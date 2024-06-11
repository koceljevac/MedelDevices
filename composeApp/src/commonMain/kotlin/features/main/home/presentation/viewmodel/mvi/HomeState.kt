package features.main.home.presentation.viewmodel.mvi

import core.utils.ViewState
import features.main.home.domain.entites.Device

sealed class HomeState : ViewState{
    data object Initial: HomeState()
    data object Loading: HomeState()
    data class DevicesLoaded(val devices: List<Device>): HomeState()
    data class Error(val message:String?): HomeState()
}
