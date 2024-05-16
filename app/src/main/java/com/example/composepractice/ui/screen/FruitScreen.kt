package com.example.composepractice.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composepractice.R
import com.example.composepractice.Screen
import com.example.composepractice.data.model.Fruit


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitScreen(navController: NavController, fruitList: List<Fruit>) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Hello, Dario!", fontWeight = FontWeight.Bold, fontSize = 30.sp) },
            actions = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User Account")
            },
            colors = topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color(35, 120, 60),
                actionIconContentColor = Color.Black
            ),
            modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp)
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(251, 253, 251)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FruitsForYou()
            WeekBestSellersCard()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(fruitList.take(3), key = { it.id }) { fruit ->
                    FruitCard(
                        fruit,
                        onFruitClick = { navController.navigate(Screen.FruitDetailScreen.route + "/${fruit.id}") })
                }
            }
        }
    }
}


@Composable
fun WeekBestSellersCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "Week's bestsellers",
            fontWeight = FontWeight.Bold,
            color = Color(35, 120, 60),
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(60.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(135, 206, 150)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(50.dp)
                .width(55.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                contentDescription = "Go to week bestsellers",
                tint = Color.White,
            )
        }
    }
}

@Composable
fun FruitCard(fruit: Fruit, onFruitClick: (fruit: Fruit) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .clickable { onFruitClick(fruit) },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = fruit.image),
                contentDescription = "Fruit Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = fruit.backgroundColor)
                    .width(100.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = fruit.name, fontWeight = FontWeight.Bold, color = Color(35, 120, 60))
                Text(
                    text = "Super tasty",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(35, 120, 60)
                )
                Text(
                    text = "$" + fruit.price,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 18.dp),
                    color = Color(35, 120, 60)
                )
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Icon(
                    imageVector = Icons.TwoTone.MoreVert,
                    contentDescription = "More options",
                    tint = Color(84, 178, 106)
                )
            }
        }
    }
}

@Composable
fun FruitsForYou() {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Color(135, 206, 150)),
        shape = RoundedCornerShape(35.dp),
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .height(140.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color(183, 227, 189))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.test_apple),
                    contentDescription = "Fruits for you",
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .align(Alignment.Center)
                )
            }
            Text(
                text = "We picked up a new\nportion of fresh fruits\nfor you!",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp))
                    .background(color = Color(183, 227, 189))
                    .align(Alignment.Bottom)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = "Go to fruits for me",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

