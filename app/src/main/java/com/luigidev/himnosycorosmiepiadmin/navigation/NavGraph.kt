package com.luigidev.himnosycorosmiepiadmin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormScreen
import com.luigidev.himnosycorosmiepiadmin.home.ui.HomeScreen
import com.luigidev.himnosycorosmiepiadmin.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {

        composable(Routes.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        
        composable(Routes.HomeScreen.route) {
            HomeScreen(navigationController = navController)
        }
        
        composable(
            Routes.FormScreen.route,
            arguments = listOf(navArgument("id") { defaultValue = "" })
        ) { backStackEntry ->
            FormScreen(
                navigationController = navController,
                choirId = backStackEntry.arguments?.getString("id").orEmpty()
            )
        }
       
    }
}