package com.example.littlelemon.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    NavHost(
        navController = navController,
        startDestination = if (sharedPreferences.contains("email")) Home.route else Onboarding.route
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController, onRegister = { firstName, lastName, email ->
                sharedPreferences.edit().apply {
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("email", email)
                }.apply()
            })
        }
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            val firstName = sharedPreferences.getString("firstName", "") ?: ""
            val lastName = sharedPreferences.getString("lastName", "") ?: ""
            val email = sharedPreferences.getString("email", "") ?: ""
            Profile(navController, firstName, lastName, email) {
                sharedPreferences.edit().apply {
                    remove("firstName")
                    remove("lastName")
                    remove("email")
                }.apply()
            }
        }
    }
}