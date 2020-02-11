package com.example.opgg.domain

import com.example.opgg.common.roundUp
import com.example.opgg.data.summoner.Gene
import com.example.opgg.data.summoner.MatchData
import com.example.opgg.data.summoner.SummonerRepository
import com.example.opgg.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception
import kotlin.math.roundToInt

class StartUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    suspend operator fun invoke(): Result<Entity> {
        return try {
            withContext(Dispatchers.IO) {
                val genetoryCall = async { repository.getGenetory() }
                val matchCall = async { repository.getMatches(null) }

                val genetory: Gene.Genetory = genetoryCall.await()
                val match: MatchData.Match = matchCall.await()
                val header = MainViewModel.Header(
                    genetory.summoner.name,
                    genetory.summoner.level.toString(),
                    genetory.summoner.profileImageUrl,
                    genetory.summoner.leagues
                )

                val analysis = analyseRecentMatches(match)
                val history = match.games.map {
                    MainViewModel.History(
                        if (it.isWin) "승" else "패",
                        calcGameLength(it.gameLength),
                        it.champion.imageUrl,
                        classifyOpBadge(it.stats.general.opScoreBadge),
                        it.stats.general.kill,
                        it.stats.general.deaths,
                        it.stats.general.assist,
                        it.stats.general.contributionForKillRate,
                        it.spells.map { spell -> spell.imageUrl },
                        it.items.map { item -> item.imageUrl },
                        it.gameType,
                        timePassed(it.createDate)
                    )
                }

                Result.Success(Entity(header, analysis, history))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun analyseRecentMatches(match: MatchData.Match): MainViewModel.Analysis {
        val games = match.games
        val history = if (games.size > 20) games.subList(0, 20) else games
        val total = history.size.toDouble()

        var win: Int = 0
        var lose: Int = 0
        var kill: Int = 0
        var death: Int = 0
        var assist: Int = 0
        history.forEach {
            if (it.isWin) win++ else lose++
            kill += it.stats.general.kill
            death += it.stats.general.deaths
            assist += it.stats.general.assist
        }

        val winRate: Int = (win / total * 100).roundToInt()
        val avgKill = (kill / total).roundUp()
        val avgDeath = (death / total).roundUp()
        val avgAssist = (assist / total).roundUp()
        val kdaSummary = ((kill + assist) / death.toDouble()).roundUp(2)


        return MainViewModel.Analysis(
            "${win}승 ${lose}패",
            "$avgKill / $avgDeath / $avgAssist",
            "$kdaSummary (${winRate}%)",
            match.champions,
            match.positions
        )
    }

    private fun calcGameLength(sec: Int): String {
        val min = sec / 60
        val remainSec = sec % 60
        return "$min:$remainSec"
    }

    private fun timePassed(timestamp: Long): String {
        val passedTime = System.currentTimeMillis() / 1000 - timestamp
        return when {
            passedTime < 60 -> {
                "1분 전"
            }
            passedTime < 60 * 60 -> {
                "${passedTime / 60}분 전"
            }
            passedTime < 60 * 60 * 24 -> {
                "${passedTime / (60 * 60)}시간 전"
            }
            else -> {
                "${passedTime / (60 * 60 * 24)}일 전"
            }
        }
    }

    private fun classifyOpBadge(opBadgeString: String): MainViewModel.BadgeType {
        return when (opBadgeString) {
            "ACE" -> MainViewModel.BadgeType.ACE
            "MVP" -> MainViewModel.BadgeType.MVP
            else -> MainViewModel.BadgeType.NONE
        }
    }


    data class Entity(
        val header: MainViewModel.Header,
        val analysis: MainViewModel.Analysis,
        val history: List<MainViewModel.History>
    )
}

