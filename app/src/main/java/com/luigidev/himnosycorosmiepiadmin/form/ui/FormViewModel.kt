package com.luigidev.himnosycorosmiepiadmin.form.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.state.UIStateForm
import com.luigidev.himnosycorosmiepiadmin.form.domain.usecase.UploadChoirUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormViewModel: ViewModel() {

    private val _resultState = MutableLiveData<UIStateForm>()
    val resultState: LiveData<UIStateForm> = _resultState


    private val uploadChoirUseCase = UploadChoirUseCase()

    fun uploadChoir(choir: Choir){
        Log.i("Choir", "Valor del choir $choir")
        viewModelScope.launch(Dispatchers.IO) {
            _resultState.postValue(UIStateForm.Loading)
            withContext(Dispatchers.Main){
                when(val result = uploadChoirUseCase(choir)){
                    is ResultAPI.Error -> {
                      _resultState.value = UIStateForm.Error(result.message)
                    }
                    is ResultAPI.Success -> {
                      _resultState.value = UIStateForm.Success(result.data)
                    }
                }
            }
        }
    }

    fun goToHome(navigationController: NavHostController) {
        navigationController.navigate(Routes.HomeScreen.route)
        _resultState.value = UIStateForm.FillOut
    }

    fun addNew(){
        _resultState.value = UIStateForm.FillOut
    }


}