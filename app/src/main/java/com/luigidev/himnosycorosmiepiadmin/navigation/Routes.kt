package com.luigidev.himnosycorosmiepiadmin.navigation

sealed class Routes(val route: String) {
    object FormScreen : Routes("FormScreen?id={id}") {
        fun createRoute(id: String) = "FormScreen?id=$id"
    }

    object HomeScreen : Routes("HomeScreen")

    object SplashScreen : Routes("SplashScreen")

    object PreviewScreen : Routes("PreviewScreen?id={id}") {
        fun createRoute(id: String) = "PreviewScreen?id=$id"
    }
}