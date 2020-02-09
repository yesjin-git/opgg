package com.example.opgg.ui.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opgg.domain.StartUseCase
import com.example.opgg.domain.UpdateListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val startUseCase: StartUseCase,
    val updateListUseCase: UpdateListUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            startUseCase()
        }
    }

}
