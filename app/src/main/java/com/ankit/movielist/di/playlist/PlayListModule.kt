package com.ankit.movielist.di.playlist

import com.ankit.movielist.di.ComponentManager
import com.ankit.movielist.playlist.PlayListFragment
import dagger.Module

@Module
abstract class PlayListModule

fun inject(playListFragment: PlayListFragment) {
    DaggerPlayListComponent.builder()
        .appComponent(ComponentManager.appComponent)
        .build()
        .inject(playListFragment)
}
