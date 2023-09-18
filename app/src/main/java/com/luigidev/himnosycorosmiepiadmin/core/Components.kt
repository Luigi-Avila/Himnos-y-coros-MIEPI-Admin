package com.luigidev.himnosycorosmiepiadmin.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(
    label: String,
    textValue: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isInvalid: Boolean = false,
    supportText: String = "",
    supportTextError: String = "",
    oneLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    trailingIcon: ImageVector? = null,
    onTextChanged: (String) -> Unit,
    onClickTrailingIcon: (() -> Unit)? = null,
    showSupportText: Boolean = false
    ) {
    OutlinedTextField(
        value = textValue,
        onValueChange = onTextChanged,
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        isError = isInvalid,
        supportingText = {
            if (supportTextError.length > 3 && isInvalid){
                Text(text = supportText)
            } else if(supportText.length > 3 && showSupportText){
                Text(text = supportText)
            }
        },
        trailingIcon = {
          if(trailingIcon != null && onClickTrailingIcon != null){
              IconButton(onClick = { onClickTrailingIcon() }) {
                  Icon(imageVector = trailingIcon, contentDescription = "Icon")
              }
          }
        },
        singleLine = oneLine,
        maxLines = maxLines,
    )
}

@Composable
fun Title(textTitle: String) {
    Text(text = textTitle, style = MaterialTheme.typography.displaySmall)
}

@Composable
fun ImageFromInternet(url: String, description: String? = null, modifier: Modifier){
    AsyncImage(model = url, contentDescription = description, modifier)
}