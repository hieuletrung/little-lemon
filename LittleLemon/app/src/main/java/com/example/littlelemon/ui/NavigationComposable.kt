package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {

    }
}