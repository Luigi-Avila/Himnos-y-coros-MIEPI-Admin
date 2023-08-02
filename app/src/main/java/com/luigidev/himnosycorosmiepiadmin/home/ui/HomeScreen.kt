package com.luigidev.himnosycorosmiepiadmin.home.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.core.Routes

@Composable
fun HomeScreen(navigationController: NavHostController) {


    Text(text = "Home screen")

    FloatingActionButton(onClick = { navigationController.navigate(Routes.FormScreen.route) }) {
        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add icon")
    }
}