package com.example.randomjoke.joke.domain.model

data class Joke(
    val id: Int,
    val category: String,
    val jokeLine: String,
    val punchLine: String
)