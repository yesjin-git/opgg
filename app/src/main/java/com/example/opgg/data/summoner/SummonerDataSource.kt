package com.example.opgg.data.summoner

import javax.inject.Inject

/**
 * Summoner related remote data source from api server
 */
class SummonerRemoteDataSource @Inject constructor(
    val client: SummonerService
) : SummonerDataSource {
    override suspend fun getGenetory(): Genetory {
        return client.getGenetory()
    }

    override suspend fun getMatches(lastMatch: Int): MatchData.Match {
        return client.getMatches(lastMatch)
    }
}

interface SummonerDataSource {
    suspend fun getGenetory(): Genetory
    suspend fun getMatches(lastMatch: Int): MatchData.Match
}