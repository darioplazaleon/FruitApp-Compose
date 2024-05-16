package com.example.composepractice.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.composepractice.R
import com.example.composepractice.Screen
import com.example.composepractice.data.model.Fruit

@Composable
fun WeekBestSellersScreen(navController: NavController, fruitList: List<Fruit>) {

    Column(
        modifier = Modifier
            .border(1.dp, Color.Gray)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarBestSellers(navController)
        Column {
            Text(
                text = "Week's bestsellers",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(35, 120, 60),
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(fruitList, key = { it.id }) { fruit ->
                    FruitCard(
                        fruit,
                        onFruitClick = { navController.navigate(Screen.FruitDetailScreen.route + "/${fruit.id}") })
                }
            }
        }

    }
}

@Composable
fun TopBarBestSellers(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.11f)
            .zIndex(20f)
            .padding(start = 30.dp, end = 30.dp, top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .width(55.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(135, 206, 150))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Go Back", tint = Color.White
            )
        }
        OutlinedButton(
            onClick = { navController.navigate(Screen.CartScreen.route) },
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .width(55.dp)
                .height(50.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Go to Cart", tint = Color(35, 120, 60)
            )
        }
    }
}