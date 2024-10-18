package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.Home
import com.example.littlelemon.ui.Onboarding
import com.example.littlelemon.ui.Profile
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    fun isUserDataPresent() = sharedPreferences.contains("email")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (isUserDataPresent()) Home.route else Onboarding.route
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
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}