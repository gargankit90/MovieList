package com.ankit.movielist.di

import android.app.Application
import android.content.Context
import com.ankit.movielist.network.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(): Context = application.applicationContext

    @Provides
    @ApplicationScope
    fun provideApplication(): Application = application

    @Provides
    @ApplicationScope
    fun provideCoroutineDispatcher(): CoroutinesDispatcherProvider =
        CoroutinesDispatcherProvider()
}
