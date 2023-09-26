package com.luigidev.himnosycorosmiepiadmin.form.domain.usecase

import android.util.Log
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.repository.IFormRepository
import javax.inject.Inject

class UploadChoirUseCase @Inject constructor(private val repository: IFormRepository) {

    suspend operator fun invoke(
        choir: Choir,
        state: (ResultAPI<String>) -> Unit
    ) {
        Log.i("CHOIR", "CHoir value $choir")
        return if (choir.localThumbnail != null) {
            Log.i("CHOIR", "IS in with image")
            repository.uploadChoirWithImage(choir, state)
        } else {
            repository.uploadChoir(choir, state)
        }
    }

}