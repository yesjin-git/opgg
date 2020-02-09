package com.example.opgg

import android.util.Log
import com.example.opgg.common.BASE_URL
import com.example.opgg.data.summoner.SummonerService
import com.example.opgg.di.ApiModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        assertEquals(4, 2 + 2)
    }

    @Test
    fun fetch_summoner_data(){
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(SummonerService::class.java)

        runBlocking {
            val res = service.getSummonerGenetory()
            assertNotNull(res)
        }
    }
}


