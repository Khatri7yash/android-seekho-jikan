package com.skhojkn.seekhojikan.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.model.DataItem
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.presentation.common.Anime
import com.skhojkn.seekhojikan.presentation.common.BaseColumn
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navigation: (Screen?, Array<out Any>?) -> Unit) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var itemList: List<AnimeEntity?> by remember { mutableStateOf<List<AnimeEntity?>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getTopAnime()
    }

    LaunchedEffect(uiState) {
        if (uiState is Result.Success) {
            itemList = (uiState as Result.Success).data
        }
    }

    HomeContentScreen(
        navigation = navigation,
        onRetry = {
            scope.launch {
                viewModel.getTopAnime()
            }
        },
        uiState = uiState,
        items = itemList
    )
}

@Composable
fun HomeContentScreen(
    navigation: (Screen?, Array<out Any>?) -> Unit,
    uiState: Result<Any>,
    onRetry: () -> Unit = {},
    items: List<AnimeEntity?>
) {
    BaseScreen(
        title = "Anime",
        isEmpty = items.isEmpty(),
        onRetry = { onRetry() },
        navigation
    ) {
        BaseColumn(uiState, Modifier) {
            Anime(items, onclick = { animeItem ->
                navigation(Screen.AnimeDetailsScreen, arrayOf(animeItem.malId.toString()))
            })
        }
    }
}