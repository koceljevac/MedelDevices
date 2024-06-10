package core.network

import features.auth.data.models.UserModel
import features.auth.domain.entites.UserLogin
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(private val client: HttpClient, private val baseUrl: String) {
    suspend fun getDevices(): HttpResponse {
        return client.get("$baseUrl/api/devices") {
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun loginUser(user: UserModel): HttpResponse {
        return client.post("$baseUrl/api/login") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
    }
}