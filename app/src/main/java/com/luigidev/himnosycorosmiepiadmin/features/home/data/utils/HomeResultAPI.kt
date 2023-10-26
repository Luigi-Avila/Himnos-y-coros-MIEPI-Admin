package com.luigidev.himnosycorosmiepiadmin.features.home.data.utils

sealed class HomeResultAPI<out T> {
    data class Success<T>(val data: T) : HomeResultAPI<T>()

    data class Error(val message: String) : HomeResultAPI<Nothing>()
}