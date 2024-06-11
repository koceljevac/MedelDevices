package features.auth.domain.repository

import features.auth.data.models.JWTokenDto
import features.auth.data.models.UserModel
import io.ktor.client.statement.HttpResponse

interface AuthRepository {
    suspend fun loginUser(userLogin: UserModel):JWTokenDto
    suspend fun registerUser(userLogin: UserModel):HttpResponse
}

