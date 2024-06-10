package core.di

import core.network.ApiService
import core.network.provideHttpClient
import features.main.home.data.repository.DeviceRepositoryImpl
import features.main.home.domain.repository.DeviceRepository
import features.main.home.domain.usecase.GetRemoteDeviceUseCase
import features.main.home.presentation.viewmodel.HomeViewModel
import org.koin.dsl.module

actual val viewModelModule = module {
    factory() { HomeViewModel(get ()) }
}
actual val useCaseModule= module {
    factory { GetRemoteDeviceUseCase(get()) }
}
actual val repositoryModule = module {
    single<DeviceRepository>{ DeviceRepositoryImpl(get()) }
}
actual val apiModule = module {
    single { provideHttpClient() }
    single { ApiService(get(), "https://d97b-82-117-207-248.ngrok-free.app") }
}
