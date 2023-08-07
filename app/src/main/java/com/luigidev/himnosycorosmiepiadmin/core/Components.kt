package com.luigidev.himnosycorosmiepiadmin.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(
    label: String,
    textValue: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isInvalid: Boolean,
    supportTextError: String,
    oneLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    onTextChanged: (String) -> Unit
    ) {
    OutlinedTextField(
        value = textValue,
        onValueChange = onTextChanged,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        isError = isInvalid,
        supportingText = {
            if (isInvalid){
               Text(text = supportTextError)
            }
        },
        singleLine = oneLine,
        maxLines = maxLines
    )
}

@Composable
fun Title(textTitle: String) {
    Text(text = textTitle, style = MaterialTheme.typography.displaySmall)
}