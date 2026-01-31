package com.skhojkn.seekhojikan.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.skhojkn.seekhojikan.domain.usecase.network.Result


@Composable
fun <R> BaseColumn(
    state: Result<R>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var uiState by remember { mutableStateOf<Result<R>>(state) }

    // Update errorState whenever errorMessage changes
    LaunchedEffect(state) {
        uiState = state
    }
    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (uiState == Result.Loading) Arrangement.Center else Arrangement.Top
    ) {
        when (uiState) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.testTag("ProgressIndicator"))
            }

            is Result.Success -> {
                content()
            }

            is Result.Error -> {
                ErrorAlert(errorMessage = (uiState as Result.Error).exceptionMessage) {
                    // Clear the error state when the "OK" button is clicked
                    uiState = Result.Loading
                }
            }
        }
    }
}