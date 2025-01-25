package com.david.navigationtest.ui.navigation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.david.navigationtest.ui.navigation.ui.navigation.NavRoute

@Composable
fun ThirdScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ThirdScreen")
        Button(
            onClick = {
//                navController.popBackStack()
                navController.popBackStack(NavRoute.NavSecondScreen.route, inclusive = false)
            }
        ) {
            Text(text = "SecondScreen")
        }
        Button(
            onClick = {
//                navController.popBackStack(NavRoute.NavMainScreen.route, inclusive = false)
                navController.navigate(NavRoute.NavMainScreen.route)
            }
        ) {
            Text(text = "MainScreen")
        }
    }
}