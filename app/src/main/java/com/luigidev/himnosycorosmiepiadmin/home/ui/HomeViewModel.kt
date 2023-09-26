package com.luigidev.himnosycorosmiepiadmin.home.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.state.HomeUIState
import com.luigidev.himnosycorosmiepiadmin.home.domain.usecase.GetChoirsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val getChoirsUseCase: GetChoirsUseCase) : ViewModel() {

//    private val getChoirsUsecase = GetChoirsUsecase()

    internal var homeUIState: HomeUIState<List<Choir?>> by mutableStateOf(HomeUIState.Loading)
        private set

    /*private var _choirs = MutableLiveData<List<Choir?>>()
    val choirs: LiveData<List<Choir?>> = _choirs*/


    /*internal var mPassword: String by mutableStateOf("")
        private set*/

    init {
        getChoirs()
    }

   fun getChoirs(){
        Log.i("Hola", "Fun running")
        viewModelScope.launch(Dispatchers.IO) {

            when(val result = getChoirsUseCase()){
                is ResultAPI.Error -> {
                    homeUIState = HomeUIState.Error(result.message)
                }
                is ResultAPI.Success -> {
                    withContext(Dispatchers.Main){
                       homeUIState = HomeUIState.Success(result.data)
                    }
                }

                is ResultAPI.Loading -> {

                }
            }
        }
    }

    /*internal fun onPasswordChanged(password: String){
        mPassword = password
    }*/
}