package com.ankit.movielist.di.search

import com.ankit.movielist.di.AppComponent
import com.ankit.movielist.di.FeatureScope
import com.ankit.movielist.search.ui.search.SearchFragment
import com.ankit.movielist.search.ui.searchdetail.SearchDetailFragment
import dagger.Component

@Component(
    modules = [SearchModule::class],
    dependencies = [AppComponent::class]
)
@FeatureScope
interface SearchComponent {

    @Component.Builder
    interface Builder {
        fun build(): SearchComponent
        fun appComponent(component: AppComponent): Builder
    }

    fun inject(searchFragment: SearchFragment)
    fun inject(searchDetailFragment: SearchDetailFragment)
}
