package com.luigidev.himnosycorosmiepiadmin.features.form.domain.state

sealed class FormUIState {
    data class Success(val data: String) : FormUIState()

    data class InProgress(val progress: String) : FormUIState()

    data class Error(val message: String) : FormUIState()

    object Loading : FormUIState()

    object FillOut : FormUIState()
}