package com.ankit.movielist.search.ui.searchdetail

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
class SearchDetailViewModel @Inject constructor(
    private val searchDetailRepository: SearchDetailRepository,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private var itemExistsMutable = MutableLiveData<Boolean>()
    val itemExists: LiveData<Boolean> get() = itemExistsMutable

    private var addItemMutable = MutableLiveData<Boolean>()
    val addItem: LiveData<Boolean> get() = addItemMutable

    private var removeItemMutable = MutableLiveData<Boolean>()
    val removeItem: LiveData<Boolean> get() = removeItemMutable

    fun checkIfItemInPlayList(item: Search) {
        viewModelScope.launch(dispatcherProvider.main) {
            itemExistsMutable.value = searchDetailRepository.checkIfItemIsInPlayList(item)
        }
    }

    fun addItemInPlayList(item: Search) {
        viewModelScope.launch(dispatcherProvider.main) {
            addItemMutable.value = searchDetailRepository.addItemInPlayList(item)
        }
    }

    fun removeItemFromPlayList(item: Search) {
        viewModelScope.launch(dispatcherProvider.main) {
            removeItemMutable.value = searchDetailRepository.removeItemInPlayList(item)
        }
    }
}
