package features.main.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import core.utils.ContractViewModel
import features.main.home.domain.usecase.GetRemoteDeviceUseCase
import features.main.home.presentation.viewmodel.mvi.HomeEvent
import features.main.home.presentation.viewmodel.mvi.HomeSideEffect
import features.main.home.presentation.viewmodel.mvi.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(private  val getRemoteDeviceUseCase: GetRemoteDeviceUseCase):ContractViewModel<HomeState,HomeEvent,HomeSideEffect>(HomeState.Initial) {


    init {
        loadDevice()
    }


    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadDevices -> loadDevice()
        }
    }


    private fun loadDevice() {
        viewModelScope.launch {
            setState {
                println("Setting state to Loading") // Debugging log
                HomeState.Loading
            }
            try {
                val devices = getRemoteDeviceUseCase()
                setState {
                    println("Setting state to DevicesLoaded") // Debugging log
                    HomeState.DevicesLoaded(devices)
                }
            } catch (e: Exception) {
                setState {
                    println("Setting state to Error with message: ${e.message}") // Debugging log
                    HomeState.Error(e.message)
                }
            }
        }
    }

}