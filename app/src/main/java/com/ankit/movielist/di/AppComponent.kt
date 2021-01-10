package com.ankit.movielist.di

import dagger.Component

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
@ApplicationScope
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(module: AppModule): Builder
    }
}
