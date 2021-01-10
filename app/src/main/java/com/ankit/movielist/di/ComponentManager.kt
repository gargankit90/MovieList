package com.ankit.movielist.di

import android.app.Application

object ComponentManager {

    lateinit var application: Application

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
    }
}
