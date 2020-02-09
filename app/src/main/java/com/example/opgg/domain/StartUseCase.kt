package com.example.opgg.domain

import com.example.opgg.data.summoner.SummonerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class StartUseCase @Inject constructor(
    val repository: SummonerRepository
) {
    suspend operator fun invoke(): Result<Entity> {
        return try {
            withContext(Dispatchers.IO) {
                val genetory = launch(Dispatchers.IO) { repository.getGenetory() }
                val match = launch { repository.getMatches(0) }
                Result.Success(Entity(""))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    data class Entity(
        val result: String
    )
}

