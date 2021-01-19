package com.ankit.movielist.playlist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankit.movielist.database.TABLE_NAME_PLAYLIST
import com.ankit.movielist.search.model.Search

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(search: Search)

    @Delete
    suspend fun removeItem(search: Search)

    @Query("SELECT * FROM $TABLE_NAME_PLAYLIST WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: String): Search?

    @Query("SELECT * FROM $TABLE_NAME_PLAYLIST")
    suspend fun getAllItems(): List<Search>?
}
