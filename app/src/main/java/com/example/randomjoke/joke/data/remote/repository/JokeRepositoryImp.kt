package com.example.randomjoke.joke.data.remote.repository

import com.example.randomjoke.core.util.ApiResource
import com.example.randomjoke.joke.data.remote.mapper.toJoke
import com.example.randomjoke.joke.data.remote.retrofit.RetrofitInstance
import com.example.randomjoke.joke.domain.model.Joke
import com.example.randomjoke.joke.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepositoryImp: JokeRepository {
    override suspend fun getJoke(): Flow<ApiResource<Joke>> {
        return flow {
            emit(ApiResource.Loading)
            try {
                val joke = RetrofitInstance.jokeApi.getJoke().toJoke()
                emit(ApiResource.Success(joke))
                return@flow
            }
            catch (e: Exception) {
                emit(ApiResource.Failed(e.message))
                return@flow
            }

        }
    }

}