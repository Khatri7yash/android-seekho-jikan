package com.skhojkn.seekhojikan.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.skhojkn.seekhojikan.presentation.screens.details.AnimeDetailsScreen
import com.skhojkn.seekhojikan.presentation.screens.home.MainScreen
import com.skhojkn.seekhojikan.presentation.screens.paymentmodes.PaymentModesScreen

val LocalCurrentRoute = (compositionLocalOf<String?> { null })

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

    CompositionLocalProvider(LocalCurrentRoute provides currentRoute) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(route = Screen.PaymentModes.route) {
                PaymentModesScreen{ navigation, args ->
                    navController navigateRoute (NavigationData(navigation, args))
                }
            }
            composable(Screen.HomeScreen.route) {
                MainScreen { navigation, args ->
                    navController navigateRoute (NavigationData(navigation, args))
                }
            }
//            composable(Screen.AnimeDetailsScreen.route) {  backStackEntry ->
            composable(Screen.AnimeDetailsScreen.route.plus(Screen.AnimeDetailsScreen.argsName.joinToString { navArgument -> navArgument.name })) {  backStackEntry ->
                val key = Screen.AnimeDetailsScreen.argsName[0].name.substringAfter("{")
                        .substringBefore("}")

                val animeId = backStackEntry.arguments?.getString(key)?.toInt()
                animeId?.let { id ->
                    AnimeDetailsScreen(animeID = id) { navigation, args ->
                        navController navigateRoute (NavigationData(navigation, args))
                    }
                }
            }
        }
    }
}


private infix fun NavHostController.navigateRoute(navigationPair: NavigationData) {
    val (navigation, args) = navigationPair
    navigation?.let {
        args?.let { array ->
            this.navigate(it.route + array.joinToString(separator = "/", prefix = "/"))
        } ?: this.navigate(it.route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(this@navigateRoute.graph.startDestinationId) { saveState = true }
        }
    } ?: run { this.popBackStack() }
}