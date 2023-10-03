package com.luigidev.himnosycorosmiepiadmin.home.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.state.HomeUIState
import com.luigidev.himnosycorosmiepiadmin.home.domain.usecase.DeleteChoirUseCase
import com.luigidev.himnosycorosmiepiadmin.home.domain.usecase.GetChoirsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getChoirsUseCase: GetChoirsUseCase,
    private val deleteChoirUseCase: DeleteChoirUseCase
    ) :
    ViewModel() {

    internal var homeUIState: HomeUIState<List<Choir?>> by mutableStateOf(HomeUIState.Loading)
        private set


    init {
        getChoirs()
    }

    private fun getChoirs() {
        getChoirsUseCase { result ->
            homeUIState = when (result) {
                is HomeResultAPI.Error -> HomeUIState.Error(result.message)
                is HomeResultAPI.Success -> HomeUIState.Success(result.data)
            }
        }
    }

    fun deleteChoir(choirData: Choir, showSnackbar: (Boolean) -> Unit) {
        Log.i("VIEWMODEL", "DELETE CHOIR $choirData")
        //Refactor this for optimization
        deleteChoirUseCase(choirData){ result ->
            when(result){
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

}