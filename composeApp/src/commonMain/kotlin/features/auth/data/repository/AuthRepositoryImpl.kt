package features.auth.data.repository

import core.network.ApiService
import features.auth.data.models.JWTokenDto
import features.auth.data.models.UserModel
import features.auth.domain.repository.AuthRepository
import features.main.home.data.repository.DeviceException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    private lateinit var response: HttpResponse
    override suspend fun loginUser(userLogin: UserModel): JWTokenDto {
        response = apiService.loginUser(userLogin)
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

    override suspend fun registerUser(userRegister: UserModel): HttpResponse {
        response = apiService.registerUser(userRegister)
        return when (response.status) {
            HttpStatusCode.OK -> {
                println("Registration successful")
                response
            }
            else -> {
                val errorMessage = "Failed to register user: ${response.status.description}"
                println(errorMessage)
                throw DeviceException(errorMessage)
            }
        }
    }
}