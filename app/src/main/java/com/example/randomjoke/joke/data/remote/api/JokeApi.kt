package com.example.randomjoke.joke.data.remote.api

import retrofit2.http.GET

interface JokeApi {
    @GET("/random_joke")
    suspend fun getJoke(): JokeDto
    companion object {
        const val BASE_URL = "https://official-joke-api.appspot.com"
    }
}