package com.luigidev.himnosycorosmiepiadmin.form.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.data.models.ChoirDTO
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import javax.inject.Inject


class FormClient @Inject constructor(
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
) {


    fun uploadChoir(choir: Choir, uploadingState: (ResultAPI<String>) -> Unit) {
        try {
            val reference = db.collection(FirebaseCollections.CHOIRS.toString())
            reference.add(choir)
                .addOnSuccessListener { documentData ->
                    reference.document(documentData.id).set(choir.copy(id = documentData.id))
                        .addOnSuccessListener {
                            uploadingState(ResultAPI.Success("Success"))
                        }
                }.addOnFailureListener { error ->
                    uploadingState(
                        ResultAPI.Error(
                            error.message ?: "error"
                        )
                    )
                }
        } catch (e: Exception) {
            ResultAPI.Error(e.message.toString())
        }
    }

    fun uploadChoirWithImage(choir: Choir, state: (ResultAPI<String>) -> Unit) {
        try {
            val documentReference = db.collection(FirebaseCollections.CHOIRS.toString())
            documentReference.add(choir)
                .addOnSuccessListener { documentData ->
                    val storagePath = "images/${documentData.id}/${choir.localThumbnail?.lastPathSegment}"
                    val fileReference = storage
                        .reference.child(storagePath)
                    val uploadTask = choir.localThumbnail?.let { fileReference.putFile(it) }
                    uploadTask?.addOnProgressListener {
                        val progress =
                            (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                        state.invoke(ResultAPI.Loading(progress))
                    }?.addOnSuccessListener { task ->
                        task.storage.downloadUrl.addOnSuccessListener { url ->
                            documentReference.document(documentData.id)
                                .set(choir.copy(id = documentData.id, localThumbnail = url, storagePath = storagePath))
                                .addOnSuccessListener {
                                    state.invoke(ResultAPI.Success("Success"))
                                }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("CLIENT", "SOMETHING went wrong")
            state.invoke(ResultAPI.Error("Something went wrong"))
        }
    }

    fun getChoirById(id: String, apiState: (ResultAPI<ChoirDTO?>) -> Unit) {
        try {
            val documentReference =
                db.collection(FirebaseCollections.CHOIRS.toString()).document(id)
            apiState.invoke(ResultAPI.Loading(0.0))
            documentReference.get().addOnSuccessListener { documentData ->
                Log.i("CLIENT", "REsponse $documentData")
                apiState.invoke(ResultAPI.Success(documentData.toObject<ChoirDTO>()))
            }
        } catch (e: Exception) {
            apiState.invoke(ResultAPI.Error(e.message.orEmpty()))
        }
    }

    fun updateChoir(id: String, choir: Choir, apiState: (ResultAPI<String>) -> Unit) {
        try {
            val documentReference =
                db.collection(FirebaseCollections.CHOIRS.toString()).document(id)
            documentReference.set(choir).addOnSuccessListener { _ ->
                apiState.invoke(ResultAPI.Success("Success"))
            }
        } catch (e: Exception) {
            apiState.invoke(ResultAPI.Error(e.message.toString()))
        }
    }

    fun updateChoirWithImage(id: String, choir: Choir, apiState: (ResultAPI<String>) -> Unit) {
        try {
            val documentReference = db.collection(FirebaseCollections.CHOIRS.toString()).document(id)
            documentReference.set(choir)
                .addOnSuccessListener {
                    val fileReference = storage
                        .reference.child("images/${id}/${choir.localThumbnail?.lastPathSegment}")
                    val uploadTask = choir.localThumbnail?.let { fileReference.putFile(it) }
                    uploadTask?.addOnProgressListener {
                        val progress =
                            (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                        apiState.invoke(ResultAPI.Loading(progress))
                    }?.addOnSuccessListener { task ->
                        task.storage.downloadUrl.addOnSuccessListener { url ->
                            documentReference.update("localThumbnail", url)
                                .addOnSuccessListener {
                                    apiState.invoke(ResultAPI.Success("Success"))
                                }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("CLIENT", "SOMETHING went wrong")
            apiState.invoke(ResultAPI.Error("Something went wrong"))
        }
    }


}