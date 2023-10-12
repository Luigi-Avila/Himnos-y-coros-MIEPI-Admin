package com.luigidev.himnosycorosmiepiadmin.features.form.ui.states

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.core.ImageFromInternet
import com.luigidev.himnosycorosmiepiadmin.core.ImageFromLocal
import com.luigidev.himnosycorosmiepiadmin.core.TextFieldForm
import com.luigidev.himnosycorosmiepiadmin.features.form.ui.FormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormFillOutState(
    formViewModel: FormViewModel,
    navigationController: NavHostController
) {

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
                .verticalScroll(rememberScrollState())
        ) {
            TitleField(formViewModel)
            LyricsField(formViewModel)
            NumberField(formViewModel)
            if (formViewModel.mThumbnailUri == null && formViewModel.mStoragePath.isBlank()) {
                ThumbnailField(formViewModel)
            }
            ThumbnailPreview(
                Modifier.padding(top = dimensionResource(id = R.dimen.common_min_padding)),
                formViewModel
            )
            VideoField(formViewModel)
            VideoPreview(
                formViewModel,
                Modifier.padding(
                    top = dimensionResource(id = R.dimen.common_min_padding),
                    bottom = dimensionResource(
                        id = R.dimen.common_default_padding
                    )
                )
            )

        }
    }


}

@Composable
fun ThumbnailPreview(modifier: Modifier, formViewModel: FormViewModel) {

    if (formViewModel.isThumbnailOnScreen) {
        if (formViewModel.mThumbnailUri == null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ImageFromInternet(
                    url = formViewModel.mThumbnailURL,
                    modifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .clip(MaterialTheme.shapes.large)
                )
                if (formViewModel.mStoragePath.isNotBlank()) {
                    Button(onClick = { formViewModel.deleteImageFromStorage() }) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
                        Text(text = "Delete Image")
                    }
                }
            }
        } else {
            formViewModel.mThumbnailUri?.let {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ImageFromLocal(
                        uri = it, modifier = Modifier.padding(
                            top = dimensionResource(
                                id = R.dimen.common_min_padding
                            )
                        )
                    )
                    IconButton(onClick = { formViewModel.cancelThumbnail() }) {
                        Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                    }
                }
            }

        }
    } else {
        Card(
            modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.common_default_padding)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Icon(Icons.Outlined.PhotoLibrary, contentDescription = "")
            }
        }
    }
}

@Composable
fun VideoPreview(formViewModel: FormViewModel, modifier: Modifier) {
    if (formViewModel.isVideoPreviewOnScreen) {
        YoutubeVideo(
            videoId = formViewModel.mVideoId,
            modifier = modifier,
            formViewModel = formViewModel
        )
    } else {
        Card(
            modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.common_default_padding)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Icon(Icons.Outlined.PlayArrow, contentDescription = "")
            }
        }
    }
}

@Composable
fun NumberField(formViewModel: FormViewModel) {
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

@Composable
fun LyricsField(formViewModel: FormViewModel) {
    TextFieldForm(
        label = "Lyrics",
        textValue = formViewModel.mLyrics,
        isInvalid = formViewModel.isLyricsInvalid,
        supportTextError = "Invalid Lyrics",
        maxLines = 10,
        onTextChanged = { formViewModel.onChangeLyrics(it) })
}

@Composable
fun TitleField(formViewModel: FormViewModel) {
    TextFieldForm(
        label = "Title",
        textValue = formViewModel.mTitle,
        isInvalid = formViewModel.isTitleInvalid,
        supportTextError = "Invalid title",
        maxLines = 2,
        onTextChanged = { formViewModel.onChangeTitle(it) })
}

@Composable
fun ThumbnailField(formViewModel: FormViewModel) {

    val launcher = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            uri.path?.let {
                println("Path: $it")
            }
            println("Path URI: $uri")
            formViewModel.setThumbnailPreview(uri)
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextFieldForm(
            label = "Thumbnail",
            textValue = formViewModel.mThumbnailURL,
            trailingIcon = Icons.Filled.Cancel,
            supportTextError = "Invalid url",
            isInvalid = formViewModel.isThumbnailUrlInvalid,
            modifier = Modifier.weight(1f),
            maxLines = 4,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            onClickTrailingIcon = { formViewModel.clearThumbnailText() },
            onTextChanged = { formViewModel.onChangeThumbnail(it) },
            keyboardActions = { formViewModel.setPreviewThumbnail() },
        )
        IconButton(onClick = { formViewModel.setPreviewThumbnail() }) {
            Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "")
        }
        IconButton(onClick = { launcher.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) }) {
            Icon(imageVector = Icons.Outlined.PhotoLibrary, contentDescription = "")
        }

    }

}

@Composable
fun VideoField(formViewModel: FormViewModel) {
    TextFieldForm(
        label = "Video",
        textValue = formViewModel.mVideoUrl,
        trailingIcon = Icons.Filled.Cancel,
        isInvalid = formViewModel.isVideoUrlInvalid,
        supportTextError = "Invalid url",
        onClickTrailingIcon = { formViewModel.clearVideoText() },
        onTextChanged = { formViewModel.onChangeVideoUrl(it) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = { formViewModel.previewVideo() },
        maxLines = 4
    )
}

@Composable
fun YoutubeVideo(videoId: String, modifier: Modifier, formViewModel: FormViewModel) {
    AndroidView(
        modifier = modifier,
        factory = { formViewModel.factoryVideo(it, videoId) }
    )
}
