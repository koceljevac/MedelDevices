package features.auth.domain.repository

import features.auth.data.models.JWTokenDto
import features.auth.data.models.UserModel
import features.auth.domain.entites.UserLogin
import features.main.home.domain.entites.Device

interface AuthRepository {
    suspend fun loginUser(userLogin: UserModel):JWTokenDto
}

