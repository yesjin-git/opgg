package com.example.opgg

import com.example.opgg.common.BASE_URL
import com.example.opgg.data.summoner.SummonerService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    private val gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(SummonerService::class.java)


    @Test
    fun fetch_summoner_data(){
        runBlocking {
            val res = service.getSummonerGenetory()
            print(res)
            Assert.assertNotNull(res)
        }
    }
}