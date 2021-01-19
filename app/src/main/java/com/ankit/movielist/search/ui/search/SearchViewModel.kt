package com.ankit.movielist.search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ankit.movielist.di.FeatureScope
import com.ankit.movielist.network.CoroutinesDispatcherProvider
import com.ankit.movielist.search.PAGE_SIZE
import com.ankit.movielist.search.SearchPagingDataSource
import com.ankit.movielist.search.api.SearchApi
import com.ankit.movielist.search.model.Search
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@FeatureScope
class SearchViewModel @Inject constructor(
    private val searchApi: SearchApi,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    fun getSearchResults(query: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE)
        ) {
            SearchPagingDataSource(searchApi, query)
        }.flow.cachedIn(viewModelScope)
    }
}
