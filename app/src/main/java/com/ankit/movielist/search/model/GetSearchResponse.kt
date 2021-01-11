package com.ankit.movielist.search.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetSearchResponse(
    @Json(name = "Search")
    val search: List<Search>,

    @Json(name = "totalResults")
    val totalResults: String,

    @Json(name = "Response")
    val response: String
)

@JsonClass(generateAdapter = true)
data class Search(
    @Json(name = "Title")
    val title: String,

    @Json(name = "Year")
    val year: String,

    @Json(name = "imdbID")
    val imdbID: String,

    @Json(name = "Type")
    val type: Type,

    @Json(name = "Poster")
    val poster: String
)

enum class Type(val value: String) {
    Movie("movie"),
    Series("series"),
    Episode("episode");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "movie" -> Movie
            "series" -> Series
            "episode" -> Episode
            else -> throw IllegalArgumentException()
        }
    }
}
