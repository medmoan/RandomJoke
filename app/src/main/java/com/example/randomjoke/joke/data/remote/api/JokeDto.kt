package com.example.randomjoke.joke.data.remote.api

import com.google.gson.annotations.SerializedName

data class JokeDto(
    val id: Int?,
    @SerializedName("punchline")
    val punchLine: String?,
    @SerializedName("setup")
    val jokeLine: String?,
    @SerializedName("type")
    val category: String?
)