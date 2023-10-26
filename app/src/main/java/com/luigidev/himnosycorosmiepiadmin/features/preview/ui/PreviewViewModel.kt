package com.luigidev.himnosycorosmiepiadmin.features.preview.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luigidev.himnosycorosmiepiadmin.features.preview.domain.states.PreviewUIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel
    @Inject
    constructor() : ViewModel() {
        internal var previewUIState: PreviewUIStates by mutableStateOf(PreviewUIStates.Loading)
            private set
    }