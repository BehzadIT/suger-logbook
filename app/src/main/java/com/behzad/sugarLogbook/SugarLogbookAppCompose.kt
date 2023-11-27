package com.behzad.sugarLogbook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.behzad.sugarLogbook.features.bloodGlucose.HomeScreen

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
            HomeScreen(navController = navController)
        }
    }
}