package com.luigidev.himnosycorosmiepiadmin.features.form.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase.DeleteChoirImageUseCase
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase.GetChoirByIdUseCase
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase.UpdateChoirUseCase
import com.luigidev.himnosycorosmiepiadmin.features.form.domain.usecase.UploadChoirUseCase
import com.luigidev.himnosycorosmiepiadmin.navigation.Routes
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class FormViewModel
    @Inject
    constructor(
        private val uploadChoirUseCase: UploadChoirUseCase,
        private val getChoirByIdUseCase: GetChoirByIdUseCase,
        private val updateChoirUseCase: UpdateChoirUseCase,
        private val deleteChoirImageUseCase: DeleteChoirImageUseCase,
    ) :
    ViewModel() {
        internal var resultState: FormUIState by mutableStateOf(FormUIState.FillOut)
            private set

        private var mEditMode: Boolean by mutableStateOf(false)

        private var mChoirId: String by mutableStateOf("")

        internal var mTitle: String by mutableStateOf("")
            private set

        internal var isTitleInvalid: Boolean by mutableStateOf(false)
            private set

        internal var mLyrics: String by mutableStateOf("")
            private set

        internal var isLyricsInvalid: Boolean by mutableStateOf(false)
            private set

        internal var mNumber: String by mutableStateOf("")
            private set

        internal var isNumberInvalid: Boolean by mutableStateOf(false)
            private set

        private var isSubmitted: Boolean by mutableStateOf(false)

        internal var mVideoUrl: String by mutableStateOf("")
            private set

        internal var isVideoUrlInvalid: Boolean by mutableStateOf(false)
            private set

        internal var mVideoId: String by mutableStateOf("")
            private set

        internal var isVideoPreviewOnScreen: Boolean by mutableStateOf(false)
            private set

        internal var isThumbnailOnScreen: Boolean by mutableStateOf(false)
            private set
        internal var mThumbnailURL: String by mutableStateOf("")
            private set

        internal var isThumbnailUrlInvalid: Boolean by mutableStateOf(false)
            private set

        internal var mThumbnailUri: Uri? by mutableStateOf(null)
            private set

        internal var mStoragePath: String by mutableStateOf("")
            private set

        private fun formatText() {
            mTitle = mTitle.trim()
            mLyrics = mLyrics.trim()
            mNumber = mNumber.trim()
        }

        fun uploadChoir() {
            isSubmitted = true
            formatText()
            val validation = validateFields()
            if (validation) {
                val choir =
                    Choir(
                        id = mChoirId,
                        title = mTitle,
                        lyrics = mLyrics,
                        choirNumber = mNumber.toInt(),
                        localThumbnail = mThumbnailUri,
                        internetThumbnail = mThumbnailURL.ifBlank { null },
                        video = mVideoUrl,
                    )
                Log.i("VIEWMODEL", "CHOIR FROM VIEWMODEL $choir")
                resultState = FormUIState.Loading
                if (mEditMode) {
                    updateChoir(mChoirId, choir)
                } else {
                    uploadNewChoir(choir)
                }
            }
        }

        private fun uploadNewChoir(choir: Choir) {
            uploadChoirUseCase(choir) { result ->
                resultState =
                    when (result) {
                        is ResultAPI.Error -> {
                            FormUIState.Error(result.message)
                        }

                        is ResultAPI.Loading -> {
                            FormUIState.InProgress(result.progress.toString())
                        }

                        is ResultAPI.Success -> {
                            FormUIState.Success(result.data)
                        }
                    }
            }
        }

        private fun updateChoir(
            id: String,
            choir: Choir,
        ) {
            updateChoirUseCase(id, choir) { result ->
                resultState =
                    when (result) {
                        is ResultAPI.Error -> FormUIState.Error(result.message)
                        is ResultAPI.Loading -> FormUIState.InProgress(result.progress.toString())
                        is ResultAPI.Success -> FormUIState.Success(result.data)
                    }
            }
        }

        private fun validateFields(): Boolean {
            if (mTitle.length <= 3) isTitleInvalid = true

            if (mLyrics.length <= 3) isLyricsInvalid = true

            if (mNumber.isEmpty()) isNumberInvalid = true

            return mTitle.length > 3 && mLyrics.length > 3 && mNumber.isNotEmpty()
        }

        fun goToHome(navigationController: NavHostController) {
            navigationController.navigate(Routes.HomeScreen.route)
            resultState = FormUIState.FillOut
        }

        fun addNew() {
            resultState = FormUIState.FillOut
        }

        fun onChangeTitle(title: String) {
            mTitle = title
            isTitleInvalid = mTitle.length <= 3 && isSubmitted
        }

        fun onChangeLyrics(lyrics: String) {
            mLyrics = lyrics
            isLyricsInvalid = mLyrics.length <= 3 && isSubmitted
        }

        fun onChangeNumber(number: String) {
            mNumber = number
            isNumberInvalid =
                !(mNumber.isNotEmpty() && mNumber.isDigitsOnly() && mNumber.toInt() > 0)
        }

        fun onChangeVideoUrl(videoUrl: String) {
            mVideoUrl = videoUrl
        }

        fun onChangeThumbnail(thumbnailUrl: String) {
            mThumbnailURL = thumbnailUrl
        }

        fun previewVideo() {
            val result = getYoutubeVideoId(mVideoUrl)
            Log.i("Valor result", "$result")
            if (result != null) {
                mVideoId = result
                isVideoPreviewOnScreen = true
                isVideoUrlInvalid = false
            } else {
                isVideoUrlInvalid = true
                isVideoPreviewOnScreen = false
            }
        }

        fun setPreviewThumbnail() {
            if (mThumbnailURL.length > 3) {
                isThumbnailOnScreen = true
                isThumbnailUrlInvalid = false
            } else {
                isThumbnailOnScreen = false
                isThumbnailUrlInvalid = true
            }
        }

        fun clearThumbnailText() {
            mThumbnailURL = ""
            isThumbnailOnScreen = false
            isThumbnailUrlInvalid = true
        }

        private fun getYoutubeVideoId(url: String): String? {
            val regex =
                "(?<=watch\\?v=|/videos/|embed/|youtu\\.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=" +
                    "player_embedded&v=|%2Fvideos%2F|embed%2F|youtu\\.be%2F|%2Fv%2F)[^#&?\\n]*"
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(url)

            return if (matcher.find()) {
                matcher.group()
            } else {
                null
            }
        }

        fun factoryVideo(
            context: Context,
            videoId: String,
        ): View {
            val view = YouTubePlayerView(context)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(videoId = videoId, 0f)
                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError,
                    ) {
                        super.onError(youTubePlayer, error)
                        isVideoUrlInvalid = true
                    }
                },
            )
            return view
        }

        fun setThumbnailPreview(uri: Uri) {
            mThumbnailUri = uri
            isThumbnailOnScreen = true
            mThumbnailURL = ""
            Log.i("URI", "Uri value $uri")
        }

        fun cancelThumbnail() {
            mThumbnailUri = null
            isThumbnailOnScreen = false
        }

        fun clearVideoText() {
            mVideoUrl = ""
            isVideoPreviewOnScreen = false
            isVideoUrlInvalid = true
        }

        fun getChoir(choirId: String) {
            getChoirByIdUseCase(choirId) { result ->
                when (result) {
                    is ResultAPI.Error -> resultState = FormUIState.Error(result.message)
                    is ResultAPI.Loading -> resultState = FormUIState.Loading
                    is ResultAPI.Success -> {
                        Log.i("VIEWMODEL", "STATE changed ")
                        Log.i("VIEWMODEL", "result ${result.data}")
                        resultState = FormUIState.FillOut
                        mEditMode = true
                        with(result.data) {
                            mTitle = this.title
                            mLyrics = this.lyrics
                            mNumber = this.choirNumber.toString()
                            mThumbnailURL = this.thumbnail ?: ""
                            mVideoUrl = this.video ?: ""
                            mChoirId = this.id
                            mStoragePath = this.storagePath ?: ""
                        }
                        if (mThumbnailURL.isNotEmpty()) {
                            setPreviewThumbnail()
                        }
                        if (mVideoUrl.isNotEmpty()) {
                            previewVideo()
                        }
                    }
                }
            }
        }

        fun deleteImageFromStorage() {
            Log.i("VIEWMODEL", "Delete from storage $mStoragePath")
            resultState = FormUIState.Loading
            deleteChoirImageUseCase(mChoirId, mStoragePath) { result ->
                when (result) {
                    is ResultAPI.Error -> resultState = FormUIState.Error(result.message)
                    is ResultAPI.Loading -> resultState = FormUIState.Loading
                    is ResultAPI.Success -> {
                        resultState = FormUIState.FillOut
                        mStoragePath = ""
                        mThumbnailURL = ""
                        isThumbnailOnScreen = false
                    }
                }
            }
        }
    }