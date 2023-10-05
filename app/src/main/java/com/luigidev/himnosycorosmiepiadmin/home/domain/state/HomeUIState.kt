package com.luigidev.himnosycorosmiepiadmin.home.domain.state

sealed class HomeUIState{
    data class Error(val message: String): HomeUIState()
    object Success: HomeUIState()
    object Loading: HomeUIState()
}
