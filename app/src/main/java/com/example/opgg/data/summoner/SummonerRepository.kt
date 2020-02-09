package com.example.opgg.data.summoner

import javax.inject.Inject

/**
 * Data repository for summoner related data.
 */
class DefaultSummonerRepository @Inject constructor(
    remoteSource: SummonerDataSource
) : SummonerRepository {

}

interface SummonerRepository {
}