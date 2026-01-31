package com.skhojkn.seekhojikan.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.skhojkn.seekhojikan.domain.model.DataItem
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.presentation.common.Anime
import com.skhojkn.seekhojikan.presentation.common.BaseColumn
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.navigation.Screen


@Composable
fun MainScreen(navigation: (Screen?, Array<out Any>?) -> Unit) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    var itemList: List<DataItem?>? by remember { mutableStateOf<List<DataItem?>?>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.getTopAnime()
    }

    LaunchedEffect(uiState) {
        if(uiState is Result.Success){
            itemList = (uiState as Result.Success).data.data
        }
    }

    HomeContentScreen(
        navigation = navigation,
        uiState = uiState,
        items = itemList)
}

@Composable
fun HomeContentScreen(navigation: (Screen?, Array<out Any>?) -> Unit, uiState: Result<Any>, items: List<DataItem?>?){
    BaseScreen(
        title = "Anime",
        navigation
    ){
        BaseColumn(uiState, Modifier) {
            Anime(items, onclick = { animeItem ->

            })
        }
    }
}