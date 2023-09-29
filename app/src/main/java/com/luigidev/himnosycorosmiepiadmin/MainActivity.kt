package com.luigidev.himnosycorosmiepiadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormScreen
import com.luigidev.himnosycorosmiepiadmin.home.ui.HomeScreen
import com.luigidev.himnosycorosmiepiadmin.theme.HimnosYCorosMIEPIAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HimnosYCorosMIEPIAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.HomeScreen.route
                    ) {
                        composable(
                            Routes.FormScreen.route,
                            arguments = listOf(navArgument("id") { defaultValue = "" })
                        ) { backStackEntry ->
                            FormScreen(
                                navigationController,
                                backStackEntry.arguments?.getString("id").orEmpty()
                            )
                        }
                        composable(Routes.HomeScreen.route) { HomeScreen(navigationController) }
                    }

                }
            }
        }
    }
}