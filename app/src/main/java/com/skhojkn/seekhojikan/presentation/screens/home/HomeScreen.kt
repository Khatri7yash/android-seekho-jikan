package com.skhojkn.seekhojikan.presentation.screens.home

import androidx.compose.runtime.Composable
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.navigation.Screen


@Composable
fun MainScreen(navigation: (Screen?, Array<out Any>?) -> Unit) {
    BaseScreen(
        title = "Anime",
        navigation
    ){}
}