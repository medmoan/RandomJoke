package com.example.randomjoke.joke.presentation

data class JokeState(
    val isLoading: Boolean = false,
    val jokeLine: String = "",
    val punchLine: String = "",
    val error: String? = null
)