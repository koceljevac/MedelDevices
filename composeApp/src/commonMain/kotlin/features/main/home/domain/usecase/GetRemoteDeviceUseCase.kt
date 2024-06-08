package features.main.home.domain.usecase

import features.main.home.domain.entites.Device
import features.main.home.domain.repository.DeviceRepository

class GetRemoteDeviceUseCase(private val deviceRepository: DeviceRepository) {
    suspend operator fun invoke():List<Device>{
        return deviceRepository.getRemoveDevices()
    }
}