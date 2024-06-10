package features.main.home.data.repository

import core.network.ApiService
import features.main.home.data.mapper.DeviceMapper
import features.main.home.data.models.DeviceDTO
import features.main.home.domain.entites.Device
import features.main.home.domain.repository.DeviceRepository
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

class DeviceRepositoryImpl(
    private val apiService: ApiService
) : DeviceRepository {
    private lateinit var response: HttpResponse
    override suspend fun getRemoveDevices(): List<Device> {
        val deviceMapper = DeviceMapper()
        response = apiService.getDevices()
        return when (response.status.value) {
            200 -> {
                val deviceList: MutableList<Device> = mutableListOf()
                response.body<List<DeviceDTO>>().forEach { deviceDTO ->
                    deviceList.add(deviceMapper.fromModel(deviceDTO))
                }
                println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ${deviceList.size}")
                deviceList
            }
            else -> {
                println("Doslo do greske")
                throw DeviceException("Get all devices failed with status code: ${response.status.value}")
            }
        }
    }
}

class DeviceException(message: String) : Exception(message)
