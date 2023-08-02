package com.luigidev.himnosycorosmiepiadmin.form.ui.states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.luigidev.himnosycorosmiepiadmin.core.TextFieldForm
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormViewModel

@Composable
fun FormFillOutState(formViewModel: FormViewModel){
    var choirTitle by rememberSaveable { mutableStateOf("") }
    var choirNumber by rememberSaveable { mutableStateOf("") }
    var choirLyrics by rememberSaveable { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TextFieldForm(label = "Title", {
            choirTitle = it
        })
        TextFieldForm(label = "Lyrics", {
            choirLyrics = it
        })
        TextFieldForm(
            label = "Number", getChoir = {
                choirNumber = it
            }, KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
        Button(onClick = {
            formViewModel.uploadChoir(
                Choir(
                    title = choirTitle,
                    lyrics = choirLyrics,
                    choirNumber = choirNumber.toInt()
                )
            )
        }) {
            Text(text = "Send")
        }
    }
}