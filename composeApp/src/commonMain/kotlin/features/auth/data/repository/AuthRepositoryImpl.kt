package features.auth.data.repository

import core.network.ApiService
import features.auth.data.models.JWTokenDto
import features.auth.data.models.UserModel
import features.auth.domain.entites.UserLogin
import features.auth.domain.repository.AuthRepository
import features.main.home.data.repository.DeviceException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun loginUser(userLogin: UserModel): JWTokenDto {
        val response: HttpResponse = apiService.loginUser(userLogin)
        return when (response.status) {
            HttpStatusCode.OK -> {
                val token = response.body<JWTokenDto>()
                println("Login successful: $token")
                token
            }
            else -> {
                val errorMessage = "Failed to login user: ${response.status.description}"
                println(errorMessage)
                throw DeviceException(errorMessage)
            }
        }
    }
}