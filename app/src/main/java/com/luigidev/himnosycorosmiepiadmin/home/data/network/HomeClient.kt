package com.luigidev.himnosycorosmiepiadmin.home.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.home.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.HomeResultAPI
import com.luigidev.himnosycorosmiepiadmin.home.data.utils.toDTO
import javax.inject.Inject

class HomeClient @Inject constructor(private val db: FirebaseFirestore) {

    fun getChoirs(apiState: (HomeResultAPI<List<ChoirDTO>>) -> Unit) {
        val reference = db.collection(FirebaseCollections.CHOIRS.toString())
        try {
            reference.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    apiState.invoke(HomeResultAPI.Success(task.result.documents.map { it.toDTO() }))
                }
            }
        } catch (e: Exception) {
            apiState.invoke(HomeResultAPI.Error("Error"))
        }
    }

}