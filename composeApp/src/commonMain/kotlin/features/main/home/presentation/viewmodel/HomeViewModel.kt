package features.main.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import core.utils.ContractViewModel
import features.main.home.domain.entites.Device
import features.main.home.domain.usecase.AddDeviceUseCase
import features.main.home.domain.usecase.GetRemoteDeviceUseCase
import features.main.home.presentation.viewmodel.mvi.HomeEvent
import features.main.home.presentation.viewmodel.mvi.HomeSideEffect
import features.main.home.presentation.viewmodel.mvi.HomeState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRemoteDeviceUseCase: GetRemoteDeviceUseCase,
    private val addDeviceUseCase: AddDeviceUseCase
) : ContractViewModel<HomeState, HomeEvent, HomeSideEffect>(HomeState.Initial) {


    init {
        loadDevice()
    }


    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadDevices -> loadDevice()
            is HomeEvent.AddDevice -> addDevice(event.device)
        }
    }

    private fun addDevice(device: Device) {
        viewModelScope.launch {
            try {
                addDeviceUseCase(device)
                setState {
                    if (this is HomeState.DevicesLoaded) {
                        HomeState.DevicesLoaded(this.devices + device)
                    } else {
                        HomeState.DevicesLoaded(listOf(device))
                    }
                }
                setSideEffect { HomeSideEffect.ShowMessage("Device added successfully.") }
            } catch (e: Exception) {
                setSideEffect { HomeSideEffect.ShowMessage("Failed to add device.") }
            }
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
                println("Setting state to Error with message: ${e.message}") // Debugging log
                setState {
                    HomeState.Error(e.message)
                }
            }
        }
    }

}