package com.example.opgg.domain

import com.example.opgg.data.summoner.SummonerRepository
import javax.inject.Inject

class UpdateListUseCase @Inject constructor(
    val repository: SummonerRepository
){
    operator fun invoke(){

    }
}