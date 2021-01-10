package com.ankit.movielist.ui

import android.app.Application
import com.ankit.movielist.BuildConfig
import com.ankit.movielist.di.AppComponent
import com.ankit.movielist.di.ComponentManager
import timber.log.Timber

/**
 * Created by Gauri Gadkari on 1/9/21.
 */
class MovieApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        ComponentManager.application = this
        appComponent = ComponentManager.appComponent
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
