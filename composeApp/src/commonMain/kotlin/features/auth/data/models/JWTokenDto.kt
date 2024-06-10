package features.auth.data.models

import kotlinx.serialization.Serializable

@Serializable
data class JWTokenDto(val token:String)