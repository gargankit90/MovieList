package com.ankit.movielist.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.movielist.di.FeatureScope
import com.ankit.movielist.network.CoroutinesDispatcherProvider
import com.ankit.movielist.search.model.Search
import javax.inject.Inject
import kotlinx.coroutines.launch

@FeatureScope
class PlayListViewModel @Inject constructor(
    private val playListRepository: PlayListRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private var playListItemsMutable = MutableLiveData<List<Search>?>()
    val playListItems: LiveData<List<Search>?> get() = playListItemsMutable

    fun getAllItems() {
        viewModelScope.launch(dispatcherProvider.main) {
            playListItemsMutable.value = playListRepository.getAllItemsOfPlayList()
        }
    }
}
