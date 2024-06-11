package features.auth.domain.usecase

import features.auth.data.models.UserModel
import features.auth.domain.repository.AuthRepository
import io.ktor.client.statement.HttpResponse

class RegisterUserUseCase(private  val authRepository: AuthRepository) {
    suspend operator fun invoke(user: UserModel): HttpResponse{
        return authRepository.registerUser(user)
    }
}