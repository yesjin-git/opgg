package com.example.opgg.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opgg.domain.StartUseCase
import com.example.opgg.domain.UpdateListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.opgg.data.summoner.Gene
import com.example.opgg.data.summoner.MatchData
import com.example.opgg.domain.Result

class MainViewModel @Inject constructor(
    val startUseCase: StartUseCase,
    val updateListUseCase: UpdateListUseCase
) : ViewModel() {

    private val _header = MutableLiveData<Header>()
    val header: LiveData<Header> = _header

    private val _analysis = MutableLiveData<Analysis>()
    val analysis: LiveData<Analysis> = _analysis

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>> = _history

    init {
        viewModelScope.launch {
            startUseCase().let {
                when (it) {
                    is Result.Success -> {
                        _header.value = it.data.header
                        _analysis.value = it.data.analysis
                        _history.value = it.data.history
                    }
                    is Result.Error -> {
                        //exception will be handled by uncaught error handler
                        throw it.exception
                    }
                }
            }
        }
    }


    data class Header(
        val name: String,
        val level: String,
        val profileImageUrl: String,
        val leagues: List<Gene.League>
    )

    data class Analysis(
        val winAndLose: String,
        val kda: String,
        val kdaSummary: String,
        val champions: List<MatchData.Champion>,
        val positions: List<MatchData.Position>
    )

    data class History(
        val isWin: Boolean,
        val championImageUrl: String,
        val kda: String,
        val contributionForKillDesc: String,
        val badge: String,
        val itemUrls: List<String>,
        val gameType: String,
        val timePassed: String
    )
}
