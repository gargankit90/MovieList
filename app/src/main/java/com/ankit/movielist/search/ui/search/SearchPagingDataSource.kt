package com.ankit.movielist.search.ui.search

import androidx.paging.PagingSource
import com.ankit.movielist.search.api.FIRST_PAGE
import com.ankit.movielist.search.api.SearchApi
import com.ankit.movielist.search.model.Search
import java.io.IOException
import retrofit2.HttpException
import timber.log.Timber

const val PAGE_SIZE = 10
class SearchPagingDataSource(
    private val searchApi: SearchApi,
    private val query: String
) : PagingSource<Int, Search>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        try {
            val pageNumber = params.key ?: FIRST_PAGE
            val response = searchApi.getSearchResultForMovies(query, pageNumber)

            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val nextPageNumber: Int? =
                        if (pageNumber * PAGE_SIZE < it.totalResults.toInt()) {
                            pageNumber + 1
                        } else {
                            null
                        }
                    return LoadResult.Page(
                        data = it.search,
                        prevKey = null,
                        nextKey = nextPageNumber
                    )
                }
                return LoadResult.Error(Exception(response.message()))
            } else {
                Timber.e("Get Search activity was not successful: ${response.message()}")
                return LoadResult.Error(IOException(response.message()))
            }
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}
