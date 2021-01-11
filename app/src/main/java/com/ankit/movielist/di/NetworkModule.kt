package com.ankit.movielist.di

import com.ankit.movielist.BuildConfig
import com.ankit.movielist.search.api.SearchApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object NetworkModule {

    private const val WRITE_TIMEOUT_SECONDS = 5L
    private const val READ_TIMEOUT_SECONDS = 5L

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // This is default behavior, needs to revisit if silent retry adds overhead.
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideRetrofit(
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        url: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideServiceUrl() = "https://www.omdbapi.com/"

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideSearchApi(
        retrofit: Retrofit
    ): SearchApi {
        return retrofit
            .create(SearchApi::class.java)
    }
}
