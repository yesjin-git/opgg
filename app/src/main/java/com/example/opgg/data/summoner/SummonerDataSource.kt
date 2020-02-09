package com.example.opgg.data.summoner

import javax.inject.Inject

/**
 * Summoner related remote data source from api server
 */
class SummonerRemoteDataSource @Inject constructor(
    val client: SummonerService
) : SummonerDataSource {

}

interface SummonerDataSource {

}