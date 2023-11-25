package com.behzad.sugarLogook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.behzad.sugarLogook.features.bloodGlucose.entry.UserSearchScreen

@Composable
fun SugarLogbookAppCompose() {
    val navController = rememberNavController()
    SugarLogbookNavHost(
        navController = navController
    )
}

@Composable
fun SugarLogbookNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            UserSearchScreen(navController = navController)
        }
    }
}