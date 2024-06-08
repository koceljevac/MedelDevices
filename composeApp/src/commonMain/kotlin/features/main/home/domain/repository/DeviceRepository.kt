package features.main.home.domain.repository

import features.main.home.domain.entites.Device

interface DeviceRepository {
    suspend fun getRemoveDevices(): List<Device>
}