package com.example.randomjoke.joke.domain.repository

import com.example.randomjoke.core.util.ApiResource
import com.example.randomjoke.joke.domain.model.Joke

import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun getJoke(): Flow<ApiResource<Joke>>
}