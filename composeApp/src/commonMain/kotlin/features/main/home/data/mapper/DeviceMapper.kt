package features.main.home.data.mapper

import features.main.home.data.models.DeviceDTO
import features.main.home.domain.entites.Device

class DeviceMapper {
    fun fromModel(deviceDTO: DeviceDTO): Device{
        return  Device(
            id = deviceDTO.id,
            name = deviceDTO.name,
            status = deviceDTO.status,
            description = deviceDTO.description,
            price = deviceDTO.price,
            category = deviceDTO.category,
            isLocked = deviceDTO.isLocked,
            inventoryNumber = deviceDTO.inventoryNumber,
            image = deviceDTO.image
        )
    }

    fun toModel(device: Device):DeviceDTO{
        return DeviceDTO(
            id = device.id,
            name = device.name,
            status = device.status,
            description = device.description,
            price = device.price,
            category = device.category,
            isLocked = device.isLocked,
            inventoryNumber = device.inventoryNumber,
            image = device.image
        )
    }
}