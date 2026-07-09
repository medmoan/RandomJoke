package com.example.randomjoke.joke.data.remote.mapper

import com.example.randomjoke.joke.data.remote.api.JokeDto
import com.example.randomjoke.joke.domain.model.Joke

fun JokeDto.toJoke(): Joke {
    return Joke(
        id = id ?: -1,
        category = category ?: "",
        jokeLine = jokeLine ?: "",
        punchLine = punchLine ?: ""
    )
}