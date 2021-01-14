package com.ankit.movielist.search.api

import com.ankit.movielist.search.model.GetSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val FIRST_PAGE = 1
const val API_KEY = "7a476fcb"

interface SearchApi {

    @GET("/")
    suspend fun getSearchResultForMovies(
        @Query("s") searchQuery: String,
        @Query("page") page: Int = FIRST_PAGE,
        @Query("apikey") apiKey: String = API_KEY
    ): Response<GetSearchResponse>
}
