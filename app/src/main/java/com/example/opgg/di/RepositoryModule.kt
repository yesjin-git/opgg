package com.example.opgg.di

import com.example.opgg.data.summoner.DefaultSummonerRepository
import com.example.opgg.data.summoner.SummonerDataSource
import com.example.opgg.data.summoner.SummonerRemoteDataSource
import com.example.opgg.data.summoner.SummonerRepository
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
    fun provideSummonerRemoteDataSource(): SummonerDataSource {
        return SummonerRemoteDataSource()
    }

}
///**
// * Repositories provider
// */
//@Component
//abstract class RepositoryModule{
//
//    @Singleton
//    @Provides
//
//
//    /**
//     * Data source providers for repositories
//     */
//}