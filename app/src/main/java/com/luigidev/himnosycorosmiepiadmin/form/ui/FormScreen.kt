package com.luigidev.himnosycorosmiepiadmin.form.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormFillOutState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormInProgressState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormSuccessState


@Composable
fun FormScreen(navigationController: NavHostController, choirId: String) {

    val formViewModel = hiltViewModel<FormViewModel>()

    var editState by remember {
        mutableStateOf(choirId.isNotEmpty())
    }
    if (editState){
        formViewModel.getChoir(choirId)
        editState = false
    }


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

        is FormUIState.InProgress -> {
            FormInProgressState((formViewModel.resultState as FormUIState.InProgress).progress)
        }
    }


}