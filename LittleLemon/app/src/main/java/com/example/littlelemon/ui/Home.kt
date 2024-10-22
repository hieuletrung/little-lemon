@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)


package com.example.littlelemon.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.example.littlelemon.MenuDatabase
import com.example.littlelemon.MenuItem
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.HighlightColor1
import com.example.littlelemon.ui.theme.HighlightColor2
import com.example.littlelemon.ui.theme.Karla
import com.example.littlelemon.ui.theme.MarkaziText
import com.example.littlelemon.ui.theme.PrimaryColor1
import com.example.littlelemon.ui.theme.PrimaryColor2

val LocalDynamicDatabase = compositionLocalOf<MenuDatabase?> {
    null
}

@Composable
fun Home() {
    val database = LocalDynamicDatabase.current
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.little_lemon_logo),
                "Little Lemon Logo",
                modifier = Modifier
                    .width(179.dp)
                    .height(56.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.profile),
                "Profile Picture",
                modifier = Modifier
                    .width(50.dp)
                    .height(47.dp)
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryColor1)
        ) {
            Text(text = "Little Lemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor1)
                    .padding(top = 10.dp, start = 10.dp),
                textAlign = TextAlign.Left,
                fontSize = 40.sp,
                fontFamily = MarkaziText,
                color = PrimaryColor2
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor1)) {
                Column(modifier = Modifier.fillMaxWidth(0.60f)) {
                    Text(
                        text = "Chicago",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PrimaryColor1)
                            .padding(start = 10.dp),
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = Karla,
                        color = HighlightColor1
                    )

                    Text(
                        text = "We are family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PrimaryColor1)
                            .padding(start = 10.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                        fontFamily = Karla,
                        color = HighlightColor1
                    )
                }
                Image(
                    painterResource(id = R.drawable.hero_image),
                    "Hero Image",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(140.dp)
                        .height(140.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.FillWidth
                )
            }

            OutlinedTextField(
                value = "",
                onValueChange = { /*searchPhrase = it*/ },
                label = { Text("Enter Search Phrase") },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = HighlightColor1,
                    focusedLabelColor = HighlightColor1,
                    unfocusedLabelColor = HighlightColor2
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        }

        val databaseMenuItems = database?.menuDao()?.getAllMenuItems()?.observeAsState(initial = emptyList())

        var orderMenuItems by remember { mutableStateOf(false) }

        var menuItems = (if (orderMenuItems) {
                            databaseMenuItems?.value?.sortedBy { it.title }
                        } else databaseMenuItems?.value) ?: emptyList()

        MenuItems(items = menuItems)
    }
}

@Composable
private fun MenuItems(items: List<MenuItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(menuItem.title,
                                fontFamily = Karla,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top=5.dp, bottom=5.dp)
                            )
                            Text(menuItem.description,
                                fontFamily = Karla,
                                fontSize = 16.sp,
                                maxLines = 2,
                                modifier = Modifier.padding(top=5.dp, bottom=5.dp)
                            )
                            Text(
                                text = "$%.2f".format(menuItem.price),
                                textAlign = TextAlign.Left,
                                fontFamily = Karla,
                                fontSize = 18.sp,
                                color = PrimaryColor1,
                                modifier = Modifier.padding(top=5.dp, bottom=5.dp)
                            )
                        }
                        if (menuItem.image.isNotEmpty()) {
                            GlideImage(
                                model = menuItem.image,
                                contentDescription = menuItem.title,
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .background(Color.Gray)
                            )
                        }
                    }
                    Divider(
                        color = HighlightColor2,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}