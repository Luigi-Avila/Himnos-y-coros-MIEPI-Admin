package com.luigidev.himnosycorosmiepiadmin.form.domain.state

sealed class UIStateForm {
    data class Success(val data: String): UIStateForm()
    data class Error(val message: String): UIStateForm()
    object Loading: UIStateForm()
    object FillOut: UIStateForm()
}