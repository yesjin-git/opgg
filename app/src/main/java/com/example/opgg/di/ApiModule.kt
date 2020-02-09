package com.example.opgg.di

import com.example.opgg.common.BASE_URL
import com.example.opgg.data.summoner.SummonerService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().create()
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideSummonerClient(retrofit: Retrofit): SummonerService {
        return retrofit.create(SummonerService::class.java)
    }
}