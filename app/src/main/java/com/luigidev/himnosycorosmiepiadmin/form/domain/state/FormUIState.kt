package com.luigidev.himnosycorosmiepiadmin.form.domain.state

sealed class FormUIState {
    data class Success(val data: String): FormUIState()
    data class Error(val message: String): FormUIState()
    object Loading: FormUIState()
    object FillOut: FormUIState()
}