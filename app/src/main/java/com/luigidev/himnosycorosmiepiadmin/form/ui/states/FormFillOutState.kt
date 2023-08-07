package com.luigidev.himnosycorosmiepiadmin.form.ui.states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.core.TextFieldForm
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormFillOutState(formViewModel: FormViewModel, navigationController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Choir") },
                actions = {
                    IconButton(onClick = { formViewModel.uploadChoir() }) {
                        Icon(Icons.Outlined.Save, contentDescription = "Save info")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { formViewModel.goToHome(navigationController) }) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        },

        ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = dimensionResource(id = R.dimen.common_default_padding),
                    end = dimensionResource(id = R.dimen.common_default_padding)
                )
        ) {
            TextFieldForm(
                label = "Title",
                textValue = formViewModel.mTitle,
                isInvalid = formViewModel.isTitleInvalid,
                supportTextError = "Invalid title",
                maxLines = 2,
                onTextChanged = { formViewModel.onChangeTitle(it) })
            TextFieldForm(
                label = "Lyrics",
                textValue = formViewModel.mLyrics,
                isInvalid = formViewModel.isLyricsInvalid,
                supportTextError = "Invalid Lyrics",
                onTextChanged = { formViewModel.onChangeLyrics(it) })
            TextFieldForm(
                label = "Number",
                textValue = formViewModel.mNumber,
                isInvalid = formViewModel.isNumberInvalid,
                supportTextError = "Invalid Number",
                onTextChanged = { formViewModel.onChangeNumber(it) },
                oneLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }


}