package com.luigidev.himnosycorosmiepiadmin.form.ui.states

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FormSuccessState() {
    Dialog(onDismissRequest = {} ) {
        Row() {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Go to home")
            }
            Button(onClick = {  }) {
                Text(text = "Add new")
            }
        }
    }
}


