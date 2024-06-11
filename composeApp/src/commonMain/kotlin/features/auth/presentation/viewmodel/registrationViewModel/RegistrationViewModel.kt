package features.auth.presentation.viewmodel.registrationViewModel

import androidx.lifecycle.viewModelScope
import core.utils.ContractViewModel
import features.auth.data.models.UserModel
import features.auth.domain.usecase.RegisterUserUseCase
import features.auth.presentation.viewmodel.registrationViewModel.mvi.RegistrationEvent
import features.auth.presentation.viewmodel.registrationViewModel.mvi.RegistrationSideEffect
import features.auth.presentation.viewmodel.registrationViewModel.mvi.RegistrationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUserUseCase: RegisterUserUseCase):ContractViewModel<RegistrationState,RegistrationEvent,RegistrationSideEffect>(RegistrationState.Initial) {
    override fun onEvent(event: RegistrationEvent) {
        when(event){
            is RegistrationEvent.RegisterUser -> registerUser(event.register)
        }
    }
    private fun registerUser(user: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            setState { RegistrationState.Loading }
            try {
                registerUserUseCase.invoke(user)
                setState { RegistrationState.RegistrationSuccesful }
            } catch (e: Exception) {
                setState { RegistrationState.Error(e.message ?: "Unknown error") }
            }
        }
    }
}