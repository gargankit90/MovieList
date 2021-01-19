package com.ankit.movielist.di

import android.content.Context
import androidx.room.Room
import com.ankit.movielist.database.AppDatabase
import com.ankit.movielist.database.DATABASE_NAME
import com.ankit.movielist.playlist.dao.PlayListDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @ApplicationScope
    @Provides
    @JvmStatic
    fun provideDb(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @ApplicationScope
    @Provides
    @JvmStatic
    fun providePlayListDao(db: AppDatabase): PlayListDao {
        return db.playListDao()
    }
}
