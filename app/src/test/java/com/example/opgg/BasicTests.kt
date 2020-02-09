package com.example.opgg

import android.util.Log
import com.example.opgg.common.BASE_URL
import com.example.opgg.common.roundUp
import com.example.opgg.common.timePassed
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
        print(System.currentTimeMillis() - 1571654629000)
        assertEquals(4, 2 + 2)
    }

    @Test
    fun roundup_works_well() {

        val tester1 = 2.36
        val res1 = tester1.roundUp()

        val tester2 = 2.336
        val res2 = tester2.roundUp(2)

        assertEquals(2.4, res1, 0.toDouble())
        assertEquals(2.34, res2, 0.toDouble())
    }

    @Test
    fun time_passed_checker_works_well() {
        val currentTime = System.currentTimeMillis() / 1000

        val res1 = timePassed(currentTime - 50)
        assertEquals("1분 전", res1)


        val res2 = timePassed(currentTime - 130)
        assertEquals("2분 전", res2)

        val res3 = timePassed(currentTime - 3610)
        assertEquals("1시간 전", res3)

        val res4 = timePassed(currentTime - (2 * 60 * 60 * 24 + 10))
        assertEquals("2일 전", res4)
    }

}


