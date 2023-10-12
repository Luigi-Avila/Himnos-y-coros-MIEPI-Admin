package com.luigidev.himnosycorosmiepiadmin.features.form.ui.states

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.features.form.ui.FormViewModel

@Composable
fun FormSuccessState(formViewModel: FormViewModel, navigationController: NavHostController) {
    Dialog(onDismissRequest = {}) {
        Row() {
            Button(onClick = { formViewModel.goToHome(navigationController) }) {
                Text(text = "Go to home")
            }
            Button(onClick = { formViewModel.addNew() }) {
                Text(text = "Add new")
            }
        }
    }
}


