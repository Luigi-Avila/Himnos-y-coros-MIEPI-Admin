package com.luigidev.himnosycorosmiepiadmin.form.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.luigidev.himnosycorosmiepiadmin.core.UiState
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.UIStateForm
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormFillOutState
import com.luigidev.himnosycorosmiepiadmin.form.ui.states.FormSuccessState


@Composable
fun FormScreen(formViewModel: FormViewModel) {

    val listState: UIStateForm by formViewModel.resultState.observeAsState(initial = UIStateForm.FillOut)

    when(listState){
        is UIStateForm.Error -> { Text(text = "Something went wrong") }
        UIStateForm.FillOut -> { FormFillOutState(formViewModel =  formViewModel) }
        UIStateForm.Loading -> { Text(text = "Loading")}
        is UIStateForm.Success -> { FormSuccessState() }
    }


    
}