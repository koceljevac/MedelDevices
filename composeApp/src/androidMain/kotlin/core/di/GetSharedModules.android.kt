package core.di

import core.network.ApiService
import core.network.provideHttpClient
import dataStore
import features.auth.data.repository.AuthRepositoryImpl
import features.auth.domain.repository.AuthRepository
import features.auth.domain.usecase.UserLoginUseCase
import features.auth.presentation.viewmodel.LoginUserViewModel
import features.main.home.data.repository.DeviceRepositoryImpl
import features.main.home.domain.repository.DeviceRepository
import features.main.home.domain.usecase.GetRemoteDeviceUseCase
import features.main.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModel { HomeViewModel(get ()) }
    viewModel { LoginUserViewModel(get ()) }

}
actual val useCaseModule = module {
    factory { GetRemoteDeviceUseCase(get()) }
    factory { UserLoginUseCase(get ()) }
}
actual val repositoryModule = module {
    single<DeviceRepository>{ DeviceRepositoryImpl(get()) }
    single<AuthRepository> {AuthRepositoryImpl(get())  }
}
actual val apiModule= module {
    single { provideHttpClient() }
    single { ApiService(get(), "https://d97b-82-117-207-248.ngrok-free.app") }
}
actual val dataStore = module {
    single { dataStore(get()) }
}