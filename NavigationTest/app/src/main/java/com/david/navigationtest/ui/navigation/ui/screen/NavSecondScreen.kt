package com.david.navigationtest.ui.navigation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.david.navigationtest.ui.navigation.ui.navigation.NavRoute

@Composable
fun SecondScreen(
    navController: NavController,
    name: String,
    isOverEighteen: Boolean
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Screen")
        Text(text = "$name is over 18? ${if (isOverEighteen) "Yes" else "No"}")
        Row {
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "MainScreen")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.NavThirdScreen.route)
                }
            ) {
                Text(text = "ThirdScreen")
            }
        }

    }


}