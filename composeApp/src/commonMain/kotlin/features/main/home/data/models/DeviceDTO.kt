package features.main.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeviceDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name:String,

    @SerialName("status")
    val status:String,

    @SerialName("description")
    val description:String,

    @SerialName("price")
    val price:Float,

    @SerialName("category")
    val category:String,

    @SerialName("isLocked")
    val isLocked: Boolean,

    @SerialName("inventoryNumber")
    val inventoryNumber: String,

    @SerialName("image")
    val image: String?
)