package com.example.randomjoke.joke.data.remote.retrofit


import com.example.randomjoke.joke.data.remote.api.JokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {


    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(JokeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jokeApi: JokeApi by lazy {
        getInstance().create(JokeApi::class.java)
    }
}