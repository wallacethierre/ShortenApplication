package com.project.shortenapplication.di

import com.project.shortenapplication.data.api.AliasAPIContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://url-shortener-nu.herokuapp.com/api/"

@Module
@InstallIn(SingletonComponent::class)
object AliasNetworkModule {

    @Provides
    @Singleton
    fun provideAliasAPI(): AliasAPIContract {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AliasAPIContract::class.java)
    }
}