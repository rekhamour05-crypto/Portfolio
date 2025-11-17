package com.example.rekha.portfolioapp.di

import com.example.rekha.portfolioapp.data.remote.HoldingsApi
import com.example.rekha.portfolioapp.data.repository.HoldingsRepositoryImpl
import com.example.rekha.portfolioapp.domain.repository.HoldingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/"

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): HoldingsApi = retrofit.create(HoldingsApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: HoldingsApi): HoldingsRepository = HoldingsRepositoryImpl(api)
}
