package com.skhojkn.seekhojikan.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val title: String = "",
    val argsName: Array<out NamedNavArgument> = emptyArray()
) {

    object HomeScreen : Screen(route = "Home", title = "Anime")
    object AnimeDetailsScreen: Screen(
    route = "AnimeDetails",
    title = "Anime Details",
    argsName = arrayOf(navArgument("/{animeItem}") {
        type = NavType.IntType
        nullable = false
        defaultValue = 0
    })
    )
}