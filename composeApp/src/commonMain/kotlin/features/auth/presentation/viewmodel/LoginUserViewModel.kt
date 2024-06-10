package features.auth.presentation.viewmodel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import core.utils.ContractViewModel
import features.auth.data.models.UserModel
import features.auth.domain.usecase.UserLoginUseCase
import features.auth.presentation.viewmodel.mvi.LoginEvent
import features.auth.presentation.viewmodel.mvi.LoginSideEffect
import features.auth.presentation.viewmodel.mvi.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginUserViewModel(private val loginUseCase: UserLoginUseCase,    private val dataStore: DataStore<Preferences>
) :ContractViewModel<LoginState,LoginEvent,LoginSideEffect>(LoginState.Initial){
    override fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.LoginUser -> loginUser(event.login)
        }
    }

    private suspend fun saveToken(token: String) {
        val jwtKey = stringPreferencesKey("jwt_token")
        dataStore.edit { preferences ->
            preferences[jwtKey] = token
        }
    }
    fun fetchToken(onTokenFetched: (String?) -> Unit) {
        viewModelScope.launch {
            val token = getToken()
            onTokenFetched(token)
        }
    }

    private suspend fun getToken(): String? {
        val jwtKey = stringPreferencesKey("jwt_token")
        val preferences = dataStore.data.first()
        return preferences[jwtKey]
    }

    private fun loginUser(user: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            setState { LoginState.Loading }
            try {
                val token = loginUseCase.invoke(user)
                saveToken(token.token)
                println("Ovo je moj token $token")
                setState { LoginState.LoginSuccesful(token) }
            } catch (e: Exception) {
                println("Ovo je moja greska ${e.message}")
                setState { LoginState.Error(e.message ?: "Unknown error") }
            }
        }
    }
}