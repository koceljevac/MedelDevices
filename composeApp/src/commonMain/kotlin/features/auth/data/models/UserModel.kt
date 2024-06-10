package features.auth.data.models

import features.auth.domain.entites.JwtTokenEntity
import kotlinx.serialization.Serializable
@Serializable
data class UserModel(
    val username:String?="",
    val email: String,
    val password:String
)

@Serializable
data class JwtToken(
    val token: String
)

fun JwtToken.toEntity():JwtTokenEntity{
    return JwtTokenEntity(
        token=token
    )
}