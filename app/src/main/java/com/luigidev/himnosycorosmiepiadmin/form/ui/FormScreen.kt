package com.luigidev.himnosycorosmiepiadmin.form.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormFillOutState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormSuccessState


@Composable
fun FormScreen(navigationController: NavHostController) {

    val formViewModel = hiltViewModel<FormViewModel>()

    when (formViewModel.resultState) {
        is FormUIState.Error -> {
            Text(text = "Something went wrong")
        }

        FormUIState.FillOut -> {
            FormFillOutState(formViewModel, navigationController)
        }

        FormUIState.Loading -> {
            Text(text = "Loading")
        }

        is FormUIState.Success -> {
            FormSuccessState(formViewModel, navigationController)
        }
    }


}