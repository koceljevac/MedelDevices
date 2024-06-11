package features.main.home.domain.usecase

import features.main.home.domain.entites.Device
import features.main.home.domain.repository.DeviceRepository

class AddDeviceUseCase(private val deviceRepository: DeviceRepository) {
    suspend operator fun invoke(device: Device) {
        deviceRepository.addDevice(device)
    }
}