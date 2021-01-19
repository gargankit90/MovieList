package com.ankit.movielist.search.ui.searchdetail

import com.ankit.movielist.network.CoroutinesDispatcherProvider
import com.ankit.movielist.playlist.dao.PlayListDao
import com.ankit.movielist.search.model.Search
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.withContext

class SearchDetailRepository @Inject constructor(
    private val playListDao: PlayListDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) {

    suspend fun checkIfItemIsInPlayList(item: Search): Boolean =
        withContext(dispatcherProvider.io) {
            val item = playListDao.getItemById(item.imdbID)
            item != null
        }

    suspend fun addItemInPlayList(item: Search): Boolean =
        withContext(dispatcherProvider.io) {
            try {
                playListDao.insertItem(search = item)
                true
            } catch (exception: Exception) {
                false
            }
        }

    suspend fun removeItemInPlayList(item: Search): Boolean =
        withContext(dispatcherProvider.io) {
            try {
                playListDao.removeItem(search = item)
                true
            } catch (exception: Exception) {
                false
            }
        }
}
