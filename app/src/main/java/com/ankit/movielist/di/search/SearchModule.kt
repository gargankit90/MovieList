package com.ankit.movielist.di.search

import androidx.lifecycle.ViewModel
import com.ankit.movielist.di.ComponentManager
import com.ankit.movielist.di.ViewModelKey
import com.ankit.movielist.search.ui.SearchFragment
import com.ankit.movielist.search.ui.SearchViewModel
import com.ankit.movielist.search.ui.searchdetail.SearchDetailFragment
import com.ankit.movielist.search.ui.searchdetail.SearchDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchDetailViewModel::class)
    internal abstract fun providePlayListViewModel(viewModel: SearchDetailViewModel): ViewModel
}

fun inject(searchFragment: SearchFragment) {
    DaggerSearchComponent.builder()
        .appComponent(ComponentManager.appComponent)
        .build()
        .inject(searchFragment)
}

fun inject(searchDetailFragment: SearchDetailFragment) {
    DaggerSearchComponent.builder()
        .appComponent(ComponentManager.appComponent)
        .build()
        .inject(searchDetailFragment)
}
