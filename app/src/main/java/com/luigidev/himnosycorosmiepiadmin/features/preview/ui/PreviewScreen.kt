package com.luigidev.himnosycorosmiepiadmin.features.preview.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.features.preview.domain.states.PreviewUIStates

@Composable
fun PreviewScreen(
    navigationController: NavHostController,
    choirId: String,
) {
    val previewViewModel: PreviewViewModel = hiltViewModel()

    when (previewViewModel.previewUIState) {
        PreviewUIStates.Error -> {
            Text(text = "Error state $choirId")
        }

        PreviewUIStates.Loading -> {
            Text(text = "Loading State")
        }

        PreviewUIStates.Success -> {
            Text(text = "Success State")
        }
    }
}