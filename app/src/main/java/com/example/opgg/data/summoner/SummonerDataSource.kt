package com.example.opgg.data.summoner

import javax.inject.Inject

/**
 * Summoner related remote data source from api server
 */
class SummonerRemoteDataSource @Inject constructor(
    private val client: SummonerService
) : SummonerDataSource {
    override suspend fun getGenetory(): Gene.Genetory {
        return client.getGenetory()
    }

    override suspend fun getMatches(lastMatch: Int?): MatchData.Match {
        return client.getMatches(lastMatch)
    }
}

interface SummonerDataSource {
    suspend fun getGenetory(): Gene.Genetory
    suspend fun getMatches(lastMatch: Int?): MatchData.Match
}