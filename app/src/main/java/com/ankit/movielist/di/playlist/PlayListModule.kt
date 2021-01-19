package com.ankit.movielist.di.playlist

import androidx.lifecycle.ViewModel
import com.ankit.movielist.di.ComponentManager
import com.ankit.movielist.di.ViewModelKey
import com.ankit.movielist.playlist.PlayListFragment
import com.ankit.movielist.playlist.PlayListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlayListModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlayListViewModel::class)
    internal abstract fun providePlayListViewModel(viewModel: PlayListViewModel): ViewModel
}

fun inject(playListFragment: PlayListFragment) {
    DaggerPlayListComponent.builder()
        .appComponent(ComponentManager.appComponent)
        .build()
        .inject(playListFragment)
}
