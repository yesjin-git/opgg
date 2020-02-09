package com.example.opgg

import android.util.Log
import com.example.opgg.common.BASE_URL
import com.example.opgg.common.roundUp
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
import kotlin.math.roundToInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BasicTests {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun roundup_works_well() {
        val tester = 2.336
        val res = tester.roundUp(2)
        print(res)
        assertEquals(2.34, res, 0.toDouble())
    }

}


