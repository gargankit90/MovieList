package com.ankit.movielist.di

import android.app.Application
import android.content.Context
import com.ankit.movielist.network.CoroutinesDispatcherProvider
import com.ankit.movielist.playlist.dao.PlayListDao
import com.ankit.movielist.search.ui.searchdetail.SearchDetailRepository
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

    @Provides
    @ApplicationScope
    fun provideSearchDetailRepository(
        playListDao: PlayListDao,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): SearchDetailRepository {
        return SearchDetailRepository(
            playListDao,
            dispatcherProvider
        )
    }
}
