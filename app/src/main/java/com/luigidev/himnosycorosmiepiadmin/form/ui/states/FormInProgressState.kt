package com.luigidev.himnosycorosmiepiadmin.form.ui.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.Dialog
import com.luigidev.himnosycorosmiepiadmin.R


@Composable
fun FormInProgressState(progress: String) {
    var realProgress by remember { mutableStateOf("") }
    realProgress = progress
    LoadingDialog(realProgress)
}


@Composable
fun LoadingDialog(progress: String) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_default_padding)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Uploading Information", style = MaterialTheme.typography.titleLarge)
                Text(text = progress, style = MaterialTheme.typography.displayLarge)
                Text(text = "Progress", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}


