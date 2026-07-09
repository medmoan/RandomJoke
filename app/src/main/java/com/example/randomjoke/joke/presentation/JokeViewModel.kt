package com.example.randomjoke.joke.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomjoke.core.util.ApiResource
import com.example.randomjoke.joke.data.remote.repository.JokeRepositoryImp
import com.example.randomjoke.joke.domain.model.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JokeViewModel: ViewModel() {
    val repository = JokeRepositoryImp()
    private val _jokeState = MutableStateFlow(JokeState())
    val jokeState = _jokeState.asStateFlow()


    fun getRandomJoke() {
        viewModelScope.launch {
            repository.getJoke().collectLatest { result ->
                when(result) {
                    is ApiResource.Failed -> {
                        _jokeState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    ApiResource.Loading -> {
                        _jokeState.update {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                    is ApiResource.Success<Joke> -> {
                        _jokeState.update {
                            it.copy(
                                isLoading = false,
                                error = null
                            )
                        }
                        if (result.data == null) {
                            _jokeState.update {
                                it.copy(
                                    error = "no joke found"
                                )
                            }
                                return@collectLatest
                        }

                        if (result.data.jokeLine.isNotEmpty()) {
                            _jokeState.update {
                                it.copy(
                                    jokeLine = result.data.jokeLine,
                                    punchLine = result.data.punchLine,
                                    )
                            }
                        }
                        else {
                            _jokeState.update {
                                it.copy(
                                    error = "no joke found"
                                )

                            }
                        }

                    }
                }
            }
        }
    }
}