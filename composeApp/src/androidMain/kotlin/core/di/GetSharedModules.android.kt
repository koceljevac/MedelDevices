package core.di

import androidx.datastore.dataStore
import core.datastore.TokenRepository
import core.network.ApiService
import core.network.provideHttpClient
import features.auth.data.repository.AuthRepositoryImpl
import features.auth.domain.repository.AuthRepository
import features.auth.domain.usecase.UserLoginUseCase
import features.auth.presentation.viewmodel.LoginUserViewModel
import features.main.home.data.repository.DeviceRepositoryImpl
import features.main.home.domain.repository.DeviceRepository
import features.main.home.domain.usecase.GetRemoteDeviceUseCase
import features.main.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.single

actual val viewModelModule = module {
    viewModel { HomeViewModel(get ()) }
    viewModel { LoginUserViewModel(get (), get()) }

}
actual val useCaseModule = module {
    factory { GetRemoteDeviceUseCase(get()) }
    factory { UserLoginUseCase(get ()) }
}
actual val repositoryModule = module {
    single<DeviceRepository>{ DeviceRepositoryImpl(get()) }
    single<AuthRepository> {AuthRepositoryImpl(get())  }
    single { TokenRepository(get()) }

}
actual val apiModule= module {
    single { provideHttpClient() }
    single { ApiService(get(), "https://15a1-82-117-207-248.ngrok-free.app") }
}
actual val dataStore = module {
    single { core.dataStore(get()) }
}
