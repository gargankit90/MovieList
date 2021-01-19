package com.ankit.movielist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankit.movielist.playlist.dao.PlayListDao
import com.ankit.movielist.search.model.Search

const val DATABASE_NAME = "MovieListDb"
const val TABLE_NAME_PLAYLIST = "playlist"

@Database(entities = [Search::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playListDao(): PlayListDao
}
