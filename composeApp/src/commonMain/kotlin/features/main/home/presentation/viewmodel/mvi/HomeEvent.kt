package features.main.home.presentation.viewmodel.mvi

import core.utils.ViewEvent
import features.main.home.domain.entites.Device

sealed class HomeEvent: ViewEvent {
    data object LoadDevices : HomeEvent()

    data class AddDevice(val device: Device) : HomeEvent()
}
