package com.example.composepractice.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composepractice.Screen


@Composable
fun MasterScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)  {

        Button(onClick = { navController.navigate(Screen.FruitScreen.route) }) {
            Text(text = "Go to fruits page")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MasterPreview() {
    MasterScreen(navController = rememberNavController())
}


