package com.luigidev.himnosycorosmiepiadmin.features.home.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.features.home.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.features.home.data.utils.HomeResultAPI
import javax.inject.Inject

class HomeClient
    @Inject
    constructor(
        private val db: FirebaseFirestore,
        private val storage: FirebaseStorage,
    ) {
        fun getChoirs(apiState: (HomeResultAPI<List<ChoirDTO>>) -> Unit) {
            val reference = db.collection(FirebaseCollections.CHOIRS.toString())
            try {
                reference.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        apiState.invoke(
                            HomeResultAPI
                                .Success(
                                    task.result.documents.map {
                                        it.toObject(ChoirDTO::class.java) ?: ChoirDTO()
                                    },
                                ),
                        )
                    }
                }
            } catch (e: Exception) {
                apiState.invoke(HomeResultAPI.Error("Error"))
            }
        }

        fun deleteChoir(
            id: String,
            apiState: (HomeResultAPI<String>) -> Unit,
        ) {
            try {
                val documentReference =
                    db.collection(FirebaseCollections.CHOIRS.toString()).document(id)
                documentReference.delete()
                    .addOnSuccessListener {
                        apiState.invoke(HomeResultAPI.Success("Success"))
                    }
            } catch (e: Exception) {
                apiState.invoke(HomeResultAPI.Error(e.message.toString()))
            }
        }

        fun deleteChoirWithImage(
            id: String,
            imageFirebasePath: String,
            apiState: (HomeResultAPI<String>) -> Unit,
        ) {
            try {
                val storageReference = storage.reference.child(imageFirebasePath)
                storageReference.delete().addOnSuccessListener {
                    val documentReference =
                        db.collection(FirebaseCollections.CHOIRS.toString()).document(id)
                    documentReference.delete()
                        .addOnSuccessListener {
                            apiState.invoke(HomeResultAPI.Success("Success"))
                        }
                }.addOnFailureListener {
                    apiState.invoke(HomeResultAPI.Error(it.message.toString()))
                }
            } catch (e: Exception) {
                apiState.invoke(HomeResultAPI.Error(e.message.toString()))
            }
        }
    }