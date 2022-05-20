package com.example.starterCode.di

import com.example.starterCode.common.Constants
import com.example.starterCode.data.remote.RickAndMortyApi
import com.example.starterCode.data.repository.RickyAndMortyRepositoryImpl
import com.example.starterCode.domain.repository.RickyAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: RickAndMortyApi): RickyAndMortyRepository {
        return RickyAndMortyRepositoryImpl(api)
    }
}