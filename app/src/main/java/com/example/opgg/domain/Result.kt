package com.example.opgg.domain

sealed class Result<T> {
    data class Success<T>(
        val data: T
    ) : Result<T>()

    data class Error<T>(
        val exception: Exception
    ) : Result<T>()
}

