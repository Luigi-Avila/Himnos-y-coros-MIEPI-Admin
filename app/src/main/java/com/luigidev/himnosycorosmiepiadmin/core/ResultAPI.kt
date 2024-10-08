package com.luigidev.himnosycorosmiepiadmin.core

sealed class ResultAPI <out T>{
    data class Success<T>(val data: T): ResultAPI<T>()
    data class Error(val message: String): ResultAPI<Nothing>()
}
