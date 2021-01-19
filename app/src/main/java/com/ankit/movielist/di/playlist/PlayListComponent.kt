package com.ankit.movielist.di.playlist

import com.ankit.movielist.di.AppComponent
import com.ankit.movielist.di.FeatureScope
import com.ankit.movielist.playlist.PlayListFragment
import dagger.Component

@Component(
    modules = [PlayListModule::class],
    dependencies = [AppComponent::class]
)
@FeatureScope
interface PlayListComponent {

    @Component.Builder
    interface Builder {
        fun build(): PlayListComponent
        fun appComponent(component: AppComponent): Builder
    }

    fun inject(playListFragment: PlayListFragment)
}
