package com.luigidev.himnosycorosmiepiadmin.form.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormFillOutState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormSuccessState


@Composable
fun FormScreen(formViewModel: FormViewModel, navigationController: NavHostController) {

    val listState: FormUIState by formViewModel.resultState.observeAsState(initial = FormUIState.FillOut)

    when(listState){
        is FormUIState.Error -> { Text(text = "Something went wrong") }
        FormUIState.FillOut -> { FormFillOutState(formViewModel) }
        FormUIState.Loading -> { Text(text = "Loading")}
        is FormUIState.Success -> { FormSuccessState(formViewModel, navigationController) }
    }


    
}