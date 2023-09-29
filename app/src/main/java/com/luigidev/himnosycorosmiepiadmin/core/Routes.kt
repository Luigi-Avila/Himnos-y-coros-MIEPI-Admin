package com.luigidev.himnosycorosmiepiadmin.core

sealed class Routes(val route: String) {
    object FormScreen : Routes("FormScreen?id={id}") {
        fun createRoute(id: String) = "FormScreen?id=$id"
    }

    object HomeScreen : Routes("HomeScreen")
}
