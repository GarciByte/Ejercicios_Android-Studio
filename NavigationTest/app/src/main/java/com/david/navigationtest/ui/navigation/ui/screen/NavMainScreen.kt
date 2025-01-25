package com.david.navigationtest.ui.navigation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.david.navigationtest.ui.navigation.ui.navigation.NavRoute

@Composable
fun NavMainScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var isOverEighteen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MainScreen")
        TextField(value = name, onValueChange = { name = it }, label = { Text(text = "Name") })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Yes, i'm over the age of 18")
            Checkbox(checked = isOverEighteen, onCheckedChange = { isOverEighteen = !isOverEighteen })
        }

        Button(onClick = {
            if (name.isNotEmpty()) { // route = "second_screen/$name/$isOverEighteen"
                navController.navigate(NavRoute.NavSecondScreen.routeWithArgs(name, isOverEighteen))
            }
        }) {
            Text(text = "SecondScreen")
        }
    }

}