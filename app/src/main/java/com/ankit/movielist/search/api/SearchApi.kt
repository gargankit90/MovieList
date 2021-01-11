package com.ankit.movielist.search.api

import com.ankit.movielist.search.model.GetSearchResponse
import retrofit2.Response

interface SearchApi {

    suspend fun getSearchResultForMovies(
        searchQuery: String,
        page: Int = 1,
        apikey: String = "7a476fcb"
    ): Response<GetSearchResponse>
}
