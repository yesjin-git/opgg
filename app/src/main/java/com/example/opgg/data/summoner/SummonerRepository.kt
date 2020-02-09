package com.example.opgg.data.summoner

import javax.inject.Inject

/**
 * Data repository for summoner related data.
 */
class DefaultSummonerRepository @Inject constructor(
    private val remoteSource: SummonerDataSource
) : SummonerRepository {

    override suspend fun getGenetory(): Genetory {
        return remoteSource.getGenetory()
    }

    override suspend fun getMatches(lastMatch: Int?): MatchData.Match {
        return remoteSource.getMatches(lastMatch)
    }
}

interface SummonerRepository {
    suspend fun getGenetory(): Genetory
    suspend fun getMatches(lastMatch: Int?): MatchData.Match
}