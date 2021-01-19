package com.ankit.movielist.di

import com.ankit.movielist.playlist.dao.PlayListDao
import com.ankit.movielist.search.api.SearchApi
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
@ApplicationScope
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(module: AppModule): Builder
    }

    fun provideSearchApi(): SearchApi
    fun providePlayListDao(): PlayListDao
}
