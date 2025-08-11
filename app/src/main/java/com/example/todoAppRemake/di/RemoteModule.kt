package com.example.todoAppRemake.di

import com.example.todoAppRemake.data.remote.NotionApiService
import com.example.todoAppRemake.data.remote.deserializer.BlockDeserializer
import com.example.todoAppRemake.data.remote.model.Block
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://api.notion.com/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Block::class.java, BlockDeserializer())
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        gson: Gson,
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideNotionApiService(retrofit: Retrofit): NotionApiService = retrofit.create(NotionApiService::class.java)
}
