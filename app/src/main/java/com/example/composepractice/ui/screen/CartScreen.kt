package com.example.composepractice.ui.screen

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.composepractice.R
import com.example.composepractice.data.CartViewModel
import com.example.composepractice.data.model.Fruit

@Composable
fun Cart(navController: NavController, viewModel: CartViewModel) {

    var (isEditVisible, setIsEditVisible) = remember { mutableStateOf(false) }
    

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarCart(navController, isEditVisible, setIsEditVisible)

        ProductsList(fruitList = viewModel.getFruits(), isEditVisible, viewModel)

        Row(
            modifier = Modifier
                .fillMaxWidth(0.80f)
                .padding(top = 20.dp, bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(35, 120, 60)
            )
            Text(
                text = "$${viewModel.getTotalPrice()}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(35, 120, 60)
            )
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(135, 206, 150)),
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth(0.90f),
        ) {
            Text(text = "Check out", color = Color.White)
        }
    }
}

@Composable
fun TopBarCart(
    navController: NavController,
    isEditVisible: Boolean,
    setIsEditVisible: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(20f)
            .fillMaxHeight(0.18f)
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
            colors = ButtonDefaults.buttonColors(containerColor = Color(135, 206, 150))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Go Back", tint = Color.White
            )
        }
        OutlinedButton(
            onClick = { setIsEditVisible(!isEditVisible) }, shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .width(90.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Edit", color = Color(135, 206, 150))
        }
    }
}

@Composable
fun ProductsList(fruitList: List<Fruit>, isEditVisible: Boolean, viewModel: CartViewModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.76f)
    ) {
        Text(
            text = "You are going to buy",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color(35, 120, 60),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            items(fruitList, key = { it.id }) { fruit ->
                FruitCardCart(fruit = fruit, isEditVisible, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FruitCardCart(fruit: Fruit, isEditVisible: Boolean, viewModel: CartViewModel) {

    var count by remember { mutableIntStateOf(fruit.quantity) }
    var totalPrice by remember { mutableIntStateOf(fruit.price * count) }

    val openAlertDialog = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .clickable { },
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
                if (isEditVisible) {
                    EditProduct(
                        fruit = fruit,
                        count = count,
                        totalPrice = totalPrice,
                        onCountChange = { newCount -> count = newCount },
                        onTotalPriceChange = { newTotalPrice -> totalPrice = newTotalPrice },
                        viewModel = viewModel
                    )
                } else {
                    Text(
                        text = count.toString() + "x",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 18.dp),
                        color = Color(35, 120, 60)
                    )
                }
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Column() {
                    if (isEditVisible) {
                        Icon(imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier
                                .align(
                                    Alignment.End
                                )
                                .zIndex(20f)
                                .clickable { openAlertDialog.value = true }
                                .width(40.dp)
                                .height(25.dp),
                            tint = Color.Red
                        )
                    }
                    Text(
                        text = "$$totalPrice",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 18.dp),
                        color = Color(35, 120, 60)
                    )
                }

            }
        }
    }

    if (openAlertDialog.value) {
        AlertDialogCart(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                viewModel.removeAndRefresh(fruit);
                openAlertDialog.value = false
            })
    }
}

@Composable
fun EditProduct(
    fruit: Fruit,
    count: Int,
    onCountChange: (Int) -> Unit,
    totalPrice: Int,
    onTotalPriceChange: (Int) -> Unit,
    viewModel: CartViewModel
) {
    Row(modifier = Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        OutlinedButton(
            onClick = {
                if (count > 1) {
                    onCountChange(count - 1)
                    onTotalPriceChange(totalPrice - fruit.price)
                }
            },
            enabled = count > 1,
            contentPadding = PaddingValues(1.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                contentDescription = "Less fruit", tint = Color(35, 120, 60)
            )
        }
        Text(text = count.toString(), modifier = Modifier.padding(horizontal = 8.dp))
        OutlinedButton(
            onClick = {
                onCountChange(count + 1)
                onTotalPriceChange(totalPrice + fruit.price)
            },
            contentPadding = PaddingValues(1.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "More fruit", tint = Color(35, 120, 60),
            )
        }
    }
}


@Composable
fun AlertDialogCart(onDismissRequest: () -> Unit, onConfirmation: () -> Unit) {

    val text = "Product removed"
    val duration = Toast.LENGTH_SHORT
    val context = LocalContext.current

    val toast = Toast.makeText(context, text, duration)

    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(onClick = { onConfirmation(); toast.show() }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "Cancel")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
        },
        title = { Text(text = "Are you sure?") },
        text = { Text(text = "Do you really want to remove this item?") })
}