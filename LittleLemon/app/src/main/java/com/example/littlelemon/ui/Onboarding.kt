package com.example.littlelemon.ui

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.HighlightColor1
import com.example.littlelemon.ui.theme.HighlightColor2
import com.example.littlelemon.ui.theme.Karla
import com.example.littlelemon.ui.theme.MarkaziText
import com.example.littlelemon.ui.theme.PrimaryColor1
import com.example.littlelemon.ui.theme.PrimaryColor2

@Composable
fun ToDp(value: Int): Dp {
    return LocalDensity.current.run { value.toDp() }
}

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(onRegister: (firstName: String, lastName: String, email: String) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var enable by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.little_lemon_logo),
            "Little Lemon Logo",
            modifier = Modifier
                .align(CenterHorizontally)
                .width(179.dp)
                .height(56.dp)
        )

        Text(
            "Let's get to know you",
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor1)
                .padding(top = 20.dp, bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = Karla,
            color = HighlightColor1
        )

        Text(
            "Personal Information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 30.dp, bottom = 30.dp),
            fontSize = 18.sp,
            fontFamily = MarkaziText,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    if (it.isValidEmail()) {
                        enable = true
                    }
                },
                label = { Text("Email") }
            )
        }

        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 50.dp),) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onRegister(firstName, lastName, email)
                },
                enabled = enable,
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor2,
                    contentColor = HighlightColor2
                )
            ) {
                Text("Register")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding() { _, _, _ ->

    }
}