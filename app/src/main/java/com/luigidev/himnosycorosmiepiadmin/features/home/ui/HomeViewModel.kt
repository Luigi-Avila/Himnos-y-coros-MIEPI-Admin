package com.luigidev.himnosycorosmiepiadmin.features.home.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.state.HomeUIState
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.usecase.DeleteChoirUseCase
import com.luigidev.himnosycorosmiepiadmin.features.home.domain.usecase.GetChoirsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getChoirsUseCase: GetChoirsUseCase,
    private val deleteChoirUseCase: DeleteChoirUseCase
) :
    ViewModel() {

    internal var homeUIState: HomeUIState by mutableStateOf(HomeUIState.Loading)
        private set

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

//    private val _isSearching = MutableStateFlow(true)
//    val isSearching = _isSearching.asStateFlow()

    private val _choirs = MutableStateFlow(emptyList<Choir>())
    val choirs = searchText
        .combine(_choirs) { text, choirs ->
            if (text.isBlank()) {
                choirs
            } else {
                choirs.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _choirs.value
        )

    init {
        getChoirs()
    }

    private fun getChoirs() {
        getChoirsUseCase { result ->
            when (result) {
                is HomeResultAPI.Error -> {
                    homeUIState = HomeUIState.Error(result.message)
                }

                is HomeResultAPI.Success -> {
                    println("Result value ${result.data}")
                    _choirs.value = result.data
                    homeUIState = HomeUIState.Success
                }
            }
        }
    }

    fun deleteChoir(choirData: Choir, showSnackbar: (Boolean) -> Unit) {
        Log.i("VIEWMODEL", "DELETE CHOIR $choirData")
        //Refactor this for optimization
        deleteChoirUseCase(choirData) { result ->
            when (result) {
                is HomeResultAPI.Error -> {
                    showSnackbar(false)
                }

                is HomeResultAPI.Success -> {
                    getChoirs()
                    showSnackbar(true)
                }
            }
        }

    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun clearSearchText(){
        _searchText.value = ""
    }


}
