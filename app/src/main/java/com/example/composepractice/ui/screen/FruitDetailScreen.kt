package com.example.composepractice.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.composepractice.R
import com.example.composepractice.Screen
import com.example.composepractice.data.CartViewModel
import com.example.composepractice.data.model.Fruit

@Composable
fun FruitDetailScreen(navController: NavController, fruit: Fruit?, viewModel: CartViewModel) {


    if (fruit != null) {
        TopBarDetails(navController)
        Column(
            modifier = Modifier
                .background(color = fruit.backgroundColor)
                .fillMaxWidth()
                .fillMaxHeight()
                .zIndex(1f)
        ) {
            Image(
                painter = painterResource(id = fruit.image),
                contentDescription = "Fruit",
                modifier = Modifier.fillMaxHeight(0.5f)
            )
            InfoFruitCard(fruit = fruit, viewModel = viewModel)
        }
    } else {
        navController.popBackStack()
    }
}

@Composable
fun TopBarDetails(navController: NavController) {
    val translucentWhite = Color.White.copy(alpha = 0.5f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(20f)
            .padding(horizontal = 30.dp, vertical = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .width(55.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = translucentWhite)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Go Back", tint = Color.White
            )
        }
        Button(
            onClick = { navController.navigate(Screen.CartScreen.route) }, shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .width(55.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = translucentWhite)
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Go to Cart", tint = Color.White
            )
        }
    }
}

@Composable
fun InfoFruitCard(fruit: Fruit, viewModel: CartViewModel) {
    var count by remember { mutableIntStateOf(1) }
    var isFavorite by remember { mutableStateOf(false) }
    var totalPrice by remember { mutableIntStateOf(fruit.price) }


    val text = "Product added"
    val duration = Toast.LENGTH_SHORT
    val context = LocalContext.current

    val toast = Toast.makeText(context, text, duration)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .zIndex(10f),
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = fruit.name,
                    color = Color(35, 120, 60),
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp
                )
                Text(text = fruit.label, color = Color(35, 120, 60))
            }
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { isFavorite = !isFavorite },
                colors = iconToggleButtonColors(
                    checkedContainerColor = Color(135, 206, 150),
                    containerColor = Color(135, 206, 150)
                ),
                modifier = Modifier
                    .width(55.dp)
                    .height(50.dp),
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            }
        }
        Text(
            text = fruit.description,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(80.dp)
                .align(Alignment.CenterHorizontally)
                .border(1.dp, Color(35, 120, 60)),
            color = Color(35, 120, 60)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(start = 40.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { count = (count - 1).coerceAtLeast(1); totalPrice = (totalPrice - fruit.price).coerceAtLeast(fruit.price) },
                    contentPadding = PaddingValues(1.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.height(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                        contentDescription = "Less fruit", tint = Color(35, 120, 60)
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = count.toString(), color = Color(35, 120, 60))
                Spacer(modifier = Modifier.width(15.dp))
                OutlinedButton(
                    onClick = { count++ ; totalPrice += fruit.price},
                    contentPadding = PaddingValues(1.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.height(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "More fruit", tint = Color(35, 120, 60),
                    )
                }
            }
            Text(
                text = "$$totalPrice",
                color = Color(35, 120, 60),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { viewModel.addFruit(fruit, count); toast.show() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(135, 206, 150)),
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(0.90f),
            ) {
                Text(text = "Add to cart", color = Color.White)
            }
        }
    }
}
