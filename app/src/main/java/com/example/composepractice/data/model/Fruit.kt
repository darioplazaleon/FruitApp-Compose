package com.example.composepractice.data.model

import androidx.compose.ui.graphics.Color
import com.example.composepractice.R

data class Fruit(
    val id: Int,
    val name: String,
    val price: Int,
    val label: String,
    val description: String,
    var quantity: Int = 0,
    val backgroundColor: Color = Color.White,
    val image: Int
)

val fruitList = listOf(
    Fruit(
        0,
        "Apple",
        100,
        "Ripe and tasty",
        "The apple is a sweet, sebaceous fruit that is typically round or oblong in shape.",
        0,
        Color(212, 72, 50),
        R.drawable.apple_detail
    ),
    Fruit(
        1, "Banana", 50, "Yellow and delicious", "Yellow and delicious", 0,
        Color(228,121,164), R.drawable.banana_fruit
    ),
    Fruit(
        2, "Orange", 80, "Orange and delicious", "Orange and delicious", 0,
        Color(182,174,42), R.drawable.orange_fruit
    ),
    Fruit(
        3, "Mango", 150, "Mango and delicious", "Mango and delicious", 0,
        Color(160, 220, 217), R.drawable.mango_fruit
    ),
    Fruit(
        4, "Pineapple", 200, "Pineapple and delicious", "Pineapple and delicious", 0,
        Color(111, 84, 54), R.drawable.pineapple_fruit
    )
)

fun findFruit(id: Int) = fruitList.find { it.id == id }