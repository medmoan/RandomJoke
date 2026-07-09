package com.example.randomjoke.joke.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun JokeScreen(
    modifier: Modifier = Modifier
) {
    val jokeViewModel = viewModel<JokeViewModel>()
    val jokeState by jokeViewModel.jokeState.collectAsStateWithLifecycle()
    var showPunchLine by remember { mutableStateOf(false) }

    LaunchedEffect(jokeState.jokeLine) {
        showPunchLine = false
        if (jokeState.jokeLine.isNotEmpty()) {
            delay(1000.milliseconds)
            showPunchLine = true
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (jokeState.isLoading) {
                    CircularProgressIndicator()
                } else if (jokeState.error != null) {
                    Text(
                        text = "Error: ${jokeState.error}",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                } else if (jokeState.jokeLine.isNotEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = jokeState.jokeLine,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (showPunchLine) {
                            Text(
                                text = jokeState.punchLine,
                                style = MaterialTheme.typography.bodyLarge,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        } else {
                            // Placeholder to prevent layout jump
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = jokeViewModel::getRandomJoke,
                enabled = !jokeState.isLoading
            ) {
                Text(text = if (jokeState.jokeLine.isEmpty()) "Get Joke" else "Get Another Joke")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}