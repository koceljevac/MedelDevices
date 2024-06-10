package features.auth.domain.entites

import kotlinx.serialization.Serializable

data class UserLogin(val email:String,val password: String)