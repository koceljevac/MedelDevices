package features.main.home.presentation.viewmodel.mvi

import core.utils.ViewEvent

sealed class HomeEvent: ViewEvent {
    data object LoadDevices : HomeEvent()
}
