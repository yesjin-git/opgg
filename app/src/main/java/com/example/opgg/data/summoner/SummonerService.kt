package com.example.opgg.data.summoner

import retrofit2.http.GET
import retrofit2.http.Query

interface SummonerService {
    @GET("genetory")
    suspend fun getGenetory(): Gene.Genetory

    @GET("genetory/matches")
    suspend fun getMatches(
        @Query("lastMatch") createDate: Int?
    ): MatchData.Match

}