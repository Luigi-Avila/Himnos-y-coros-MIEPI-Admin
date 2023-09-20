package com.luigidev.himnosycorosmiepiadmin.form.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FormClient @Inject constructor(private val db: FirebaseFirestore) {



    suspend fun uploadChoir(choir: Choir): ResultAPI<String> {
        val reference = db.collection(FirebaseCollections.CHOIRS.toString()).document()
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