package features.auth.domain.usecase

import features.auth.data.models.JWTokenDto
import features.auth.data.models.UserModel
import features.auth.domain.entites.UserLogin
import features.auth.domain.repository.AuthRepository

class UserLoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: UserModel):JWTokenDto{
        return authRepository.loginUser(user)
    }
}