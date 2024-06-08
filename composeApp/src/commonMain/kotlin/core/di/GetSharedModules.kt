package core.di

import org.koin.core.module.Module

expect val viewModelModule: Module
expect val useCaseModule: Module
expect val repositoryModule: Module
expect val apiModule: Module

fun getSharedModules(): List<Module> = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)