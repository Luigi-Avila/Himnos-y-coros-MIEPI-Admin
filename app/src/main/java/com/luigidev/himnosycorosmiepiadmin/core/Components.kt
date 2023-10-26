package com.luigidev.himnosycorosmiepiadmin.core

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldForm(
    modifier: Modifier = Modifier,
    label: String,
    textValue: String,
    showSupportText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isInvalid: Boolean = false,
    supportText: String = "",
    supportTextError: String = "",
    oneLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    trailingIcon: ImageVector? = null,
    onTextChanged: (String) -> Unit,
    keyboardActions: (() -> Unit)? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
) {
    var keyboardController: SoftwareKeyboardController? = null

    if (keyboardActions != null) {
        keyboardController = LocalSoftwareKeyboardController.current
    }

    OutlinedTextField(
        value = textValue,
        onValueChange = onTextChanged,
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        keyboardActions =
            KeyboardActions(
                onDone = {
                    if (keyboardActions != null && keyboardController != null) {
                        keyboardController.hide()
                        keyboardActions()
                    }
                },
            ),
        isError = isInvalid,
        supportingText = {
            if (isInvalid) {
                Text(text = supportTextError)
            } else if (showSupportText) {
                Text(text = supportText)
            }
        },
        trailingIcon = {
            if (trailingIcon != null && onClickTrailingIcon != null && textValue.length > 3) {
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
fun ImageFromInternet(
    url: String,
    description: String? = null,
    modifier: Modifier,
) {
    AsyncImage(model = url, contentDescription = description, modifier)
}

@Composable
fun ImageFromLocal(
    uri: Uri,
    modifier: Modifier,
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .build(),
        )
    Image(
        painter = painter,
        contentDescription = "",
        modifier =
            modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(MaterialTheme.shapes.large),
    )
}