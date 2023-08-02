package com.luigidev.himnosycorosmiepiadmin.form.data.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception


class FormClient {



    suspend fun uploadChoir(choir: Choir): ResultAPI<String> {
        val db = Firebase.firestore
        val reference = db.collection("choirs").document()
        return withContext(Dispatchers.IO){
            try {
                val result = reference.set(choir).await()
                Log.i("Client", "ResultValue ${result.toString()}")
                ResultAPI.Success("Data saved")
            } catch (e: Exception){
                ResultAPI.Error(e.message.toString())
            }
        }

    }

}