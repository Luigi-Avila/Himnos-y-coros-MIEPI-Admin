package com.luigidev.himnosycorosmiepiadmin.core

sealed class Routes(val route: String){
    object FormScreen: Routes("FormScreen")
    object HomeScreen: Routes("HomeScreen")
}
