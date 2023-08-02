package com.luigidev.himnosycorosmiepiadmin.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(label: String, getChoir: (String) -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions.Default) {

    var aux by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = aux,
        onValueChange = {
            getChoir(it)
            aux = it
        },
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}