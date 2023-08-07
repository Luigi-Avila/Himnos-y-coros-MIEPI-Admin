package com.luigidev.himnosycorosmiepiadmin.form.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.FormUIState
import com.luigidev.himnosycorosmiepiadmin.form.domain.usecase.UploadChoirUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormViewModel : ViewModel() {

    private val _resultState = MutableLiveData<FormUIState>()
    val resultState: LiveData<FormUIState> = _resultState

    private val uploadChoirUseCase = UploadChoirUseCase()

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

    private fun formatText(){
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
                _resultState.postValue(FormUIState.Loading)
                withContext(Dispatchers.Main) {
                    when (val result = uploadChoirUseCase(choir)) {
                        is ResultAPI.Error -> {
                            _resultState.value = FormUIState.Error(result.message)
                        }

                        is ResultAPI.Success -> {
                            _resultState.value = FormUIState.Success(result.data)

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
        _resultState.value = FormUIState.FillOut
    }

    fun addNew() {
        _resultState.value = FormUIState.FillOut
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

}