package features.main.profile.domain.repository

interface ProfileRepository {
    suspend fun logoutUser():Boolean
}