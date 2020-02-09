package com.example.opgg.di

import com.example.opgg.data.summoner.*
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSummonerRepository(
        @Named("SummonerRemoteDataSource") remote: SummonerDataSource
    ): SummonerRepository {
        return DefaultSummonerRepository(remote)
    }


    @Singleton
    @Provides
    @Named("SummonerRemoteDataSource")
    fun provideSummonerRemoteDataSource(
        client:SummonerService
    ): SummonerDataSource {
        return SummonerRemoteDataSource(client)
    }

}
