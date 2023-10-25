package com.luigidev.himnosycorosmiepiadmin.features.preview.ui.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.luigidev.himnosycorosmiepiadmin.features.preview.ui.PreviewViewModel

@Composable
fun PreviewSuccess(previewViewModel: PreviewViewModel, choirId: String){
    Box(modifier = Modifier.fillMaxSize().background(Color.Red)){

    }
//    AndroidView(factory = { previewViewModel.factoryVideo(it) })
}
