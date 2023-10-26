package com.luigidev.himnosycorosmiepiadmin.features.preview.domain.states

sealed class PreviewUIStates {
    object Success : PreviewUIStates()

    object Loading : PreviewUIStates()

    object Error : PreviewUIStates()
}