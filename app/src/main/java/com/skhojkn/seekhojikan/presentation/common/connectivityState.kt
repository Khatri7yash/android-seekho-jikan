package com.skhojkn.seekhojikan.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.skhojkn.seekhojikan.domain.usecase.network.ConnectionState

@Composable
fun connectivityState(
    baseViewmodel: BaseViewmodel = hiltViewModel<BaseViewmodel>()): State<ConnectionState> {
    return produceState<ConnectionState>(initialValue = ConnectionState.Unavailable) {
        baseViewmodel.isOnline.collect { value = it }
    }
}