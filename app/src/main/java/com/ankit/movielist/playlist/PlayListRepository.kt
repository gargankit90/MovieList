package com.ankit.movielist.playlist

import com.ankit.movielist.network.CoroutinesDispatcherProvider
import com.ankit.movielist.playlist.dao.PlayListDao
import com.ankit.movielist.search.model.Search
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.withContext

class PlayListRepository @Inject constructor(
    private val playListDao: PlayListDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) {

    suspend fun getAllItemsOfPlayList(): List<Search>? =
        withContext(dispatcherProvider.io) {
            try {
                playListDao.getAllItems()
            } catch (exception: Exception) {
                null
            }
        }
}
