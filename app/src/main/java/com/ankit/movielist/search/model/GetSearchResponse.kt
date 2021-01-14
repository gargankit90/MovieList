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
    val type: String,

    @Json(name = "Poster")
    val poster: String
)
