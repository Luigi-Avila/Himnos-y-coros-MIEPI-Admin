package com.luigidev.himnosycorosmiepiadmin.home.domain.state

sealed class HomeUIState<out T>{
    data class Success<T>(val data: T): HomeUIState<T>()
    data class Error(val message: String): HomeUIState<Nothing>()
    object Loading: HomeUIState<Nothing>()
}
