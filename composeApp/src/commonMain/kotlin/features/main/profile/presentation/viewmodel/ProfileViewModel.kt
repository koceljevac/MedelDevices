package features.main.profile.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import core.utils.ContractViewModel
import features.main.profile.domain.usecases.LogoutUserUseCase
import features.main.profile.presentation.viewmodel.mvi.ProfileEvent
import features.main.profile.presentation.viewmodel.mvi.ProfileSideEffect
import features.main.profile.presentation.viewmodel.mvi.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ProfileViewModel(private val logoutUserUseCase: LogoutUserUseCase):ContractViewModel<ProfileState,ProfileEvent,ProfileSideEffect>(ProfileState.Initial) {
    override fun onEvent(event: ProfileEvent) {
        when(event){
            is ProfileEvent.LogoutUser -> logoutUser()
        }
    }

    private fun logoutUser(){
        viewModelScope.launch(Dispatchers.IO) {
            if(logoutUserUseCase.invoke()){
                setState { ProfileState.LogoutSuccesful }
            }else{
                setState { ProfileState.LogoutUnsuccessful("Ne") }
            }
        }
    }
}