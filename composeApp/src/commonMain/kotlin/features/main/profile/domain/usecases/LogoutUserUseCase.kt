package features.main.profile.domain.usecases

import features.main.profile.domain.repository.ProfileRepository

class LogoutUserUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): Boolean {
        return profileRepository.logoutUser()
    }
}