package org.example.project

import android.app.Application
import core.di.KoinInitializer

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}