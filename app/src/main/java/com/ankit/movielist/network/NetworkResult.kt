package com.ankit.movielist.network

import java.io.IOException
import timber.log.Timber

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Loading<out T>(val data: T? = null) : NetworkResult<T>()
    data class Error(val exception: Exception, val errorCode: Int = 0) : NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading<*> -> "Loading[data=$data]"
        }
    }
}

@Suppress("TooGenericExceptionCaught")
suspend fun <T : Any> safeApiCall(
    call: suspend () -> NetworkResult<T>,
    errorMessage: String
): NetworkResult<T> {
    return try {
        call()
    } catch (e: Exception) {
        Timber.e(e)
        NetworkResult.Error(IOException(errorMessage, e))
    }
}
