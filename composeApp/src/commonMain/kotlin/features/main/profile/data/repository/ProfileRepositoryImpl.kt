package features.main.profile.data.repository

import androidx.datastore.preferences.core.edit
import core.datastore.TokenRepository
import core.di.dataStore
import features.main.profile.domain.repository.ProfileRepository
import org.koin.compose.koinInject

class ProfileRepositoryImpl(private val tokenRepository: TokenRepository):ProfileRepository {

    override suspend fun logoutUser(): Boolean {
        return tokenRepository.clearToken()
    }
}