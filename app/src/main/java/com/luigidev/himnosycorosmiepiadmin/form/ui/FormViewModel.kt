package com.luigidev.himnosycorosmiepiadmin.form.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.form.domain.usecase.UploadChoirUseCase
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val uploadChoirUseCase: UploadChoirUseCase) : ViewModel() {
    internal var resultState: FormUIState by mutableStateOf(FormUIState.FillOut)
        private set

//    private val uploadChoirUseCase = UploadChoirUseCase()

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
            val choir = Choir(
                title = mTitle,
                lyrics = mLyrics,
                choirNumber = mNumber.toInt()
            )
            viewModelScope.launch(Dispatchers.IO) {
                resultState = FormUIState.Loading
                withContext(Dispatchers.Main) {
                    when (val result = uploadChoirUseCase(choir)) {
                        is ResultAPI.Error -> {
                            resultState = FormUIState.Error(result.message)
                        }

                        is ResultAPI.Success -> {
                            resultState = FormUIState.Success(result.data)

                        }
                    }
                }
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

    fun onChangeThumbnail(thumbnailUrl: String){
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

    fun setPreviewThumbnail(){
        if (mThumbnailURL.length > 3){
            isThumbnailOnScreen = true
            isThumbnailUrlInvalid = false
        } else {
            isThumbnailOnScreen = false
            isThumbnailUrlInvalid = true
        }

    }

    fun clearThumbnailText(){
        mThumbnailURL = ""
        isThumbnailOnScreen = false
        isThumbnailUrlInvalid = true
    }

    private fun getYoutubeVideoId(url: String): String? {
        val regex =
            "(?<=watch\\?v=|/videos/|embed/|youtu\\.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu\\.be%2F|%2Fv%2F)[^#&?\\n]*"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(url)

        return if (matcher.find()) {
            matcher.group()
        } else {
            null
        }
    }


    fun factoryVideo(context: Context, videoId: String): View {
        val view = YouTubePlayerView(context)
        view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId = videoId, 0f)
                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                    super.onError(youTubePlayer, error)
                    isVideoUrlInvalid = true
                }

            }
        )
        return view
    }

    fun setThumbnailPreview(uri: Uri){
        mThumbnailUri = uri
        isThumbnailOnScreen = true
        Log.i("URI", "Uri value $uri")
    }

    fun cancelThumbnail(){
        mThumbnailUri = null
        isThumbnailOnScreen = false
    }

}