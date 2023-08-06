package com.luigidev.himnosycorosmiepiadmin.home.data.network

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeClient {

    suspend fun getChoirs(): ResultAPI<List<DocumentSnapshot>> {
        val db = Firebase.firestore
        val reference = db.collection(FirebaseCollections.CHOIRS.toString())

        return withContext(Dispatchers.IO) {
            try {
                val result = reference.get().await()
                ResultAPI.Success(result.documents)
            } catch (e: Exception) {
                ResultAPI.Error(e.message.toString())
            }

        }


    }
}