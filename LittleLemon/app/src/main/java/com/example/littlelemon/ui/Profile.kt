package com.example.littlelemon.ui

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.HighlightColor1
import com.example.littlelemon.ui.theme.HighlightColor2
import com.example.littlelemon.ui.theme.Karla
import com.example.littlelemon.ui.theme.MarkaziText
import com.example.littlelemon.ui.theme.PrimaryColor1
import com.example.littlelemon.ui.theme.PrimaryColor2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController?, firstName: String, lastName: String, email: String, onLogout : () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.little_lemon_logo),
            "Little Lemon Logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(179.dp)
                .height(56.dp)
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
                onValueChange = { },
                enabled = false,
                label = { Text("First name") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { },
                enabled = false,
                label = { Text("Last name") }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { },
                enabled = false,
                label = { Text("Email") }
            )
        }

        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 50.dp),) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onLogout()
                    navController?.navigate(Onboarding.route)
                },
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor2,
                    contentColor = HighlightColor2
                )
            ) {
                Text("Log out")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(null,"First", "Last", "hi@gmail.com") {}
}