package core.network

import features.main.home.data.models.DeviceDTO
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

    suspend fun addDevice(device: DeviceDTO): HttpResponse {
        return client.post("$baseUrl/api/devices") {
            contentType(ContentType.Application.Json)
            setBody(device)
        }
    }
}