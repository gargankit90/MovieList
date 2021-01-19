package com.ankit.movielist.search.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ankit.movielist.database.TABLE_NAME_PLAYLIST
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class GetSearchResponse(
    @Json(name = "Search")
    val search: List<Search>?,

    @Json(name = "totalResults")
    val totalResults: String?,

    @Json(name = "Response")
    val response: String,

    @Json(name = "Error")
    val error: String?
)

@Entity(tableName = TABLE_NAME_PLAYLIST)
@Parcelize
@JsonClass(generateAdapter = true)
data class Search(

    @ColumnInfo(name = "title")
    @Json(name = "Title")
    val title: String,

    @ColumnInfo(name = "year")
    @Json(name = "Year")
    val year: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Json(name = "imdbID")
    val imdbID: String,

    @ColumnInfo(name = "type")
    @Json(name = "Type")
    val type: String,

    @ColumnInfo(name = "poster")
    @Json(name = "Poster")
    val poster: String
) : Parcelable
