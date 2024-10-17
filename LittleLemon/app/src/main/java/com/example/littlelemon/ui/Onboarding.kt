package com.example.littlelemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column() {
        Image(
            painterResource(id = R.drawable.little_lemon_logo),
            "Little Lemon Logo",
            modifier = Modifier.align(CenterHorizontally)
        )
        Text("Let's get to know you",
            modifier = Modifier
                .align(CenterHorizontally)
                //.width(400.dp)
                .background(Color(android.graphics.Color.parseColor("#495E57"))),
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.markazi_text_regular))
        )
        Text(
            "Personal Information",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") }
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") }
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Button(
            onClick = {},
            shape = RoundedCornerShape(30),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#F4CE14")),
                contentColor = Color(android.graphics.Color.parseColor("#333333"))
            )
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}