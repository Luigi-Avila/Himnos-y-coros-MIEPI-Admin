package com.luigidev.himnosycorosmiepiadmin.form.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.luigidev.himnosycorosmiepiadmin.core.FirebaseCollections
import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FormClient @Inject constructor(
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
) {


    suspend fun uploadChoir(choir: Choir, uploadingState: (ResultAPI<String>) -> Unit) {
        val reference = db.collection(FirebaseCollections.CHOIRS.toString())
        return withContext(Dispatchers.IO) {
            try {
                reference.add(choir)
                    .addOnSuccessListener { documentData ->
                        reference.document(documentData.id).set(choir.copy(id = documentData.id)).addOnSuccessListener {
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

    }

    suspend fun uploadChoirWithImage(choir: Choir, state: (ResultAPI<String>) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                val documentReference = db.collection(FirebaseCollections.CHOIRS.toString())
                documentReference.add(choir)
                    .addOnSuccessListener { documentData ->
                        val fileReference = storage
                            .reference.child("images/${documentData.id}/${choir.localThumbnail?.lastPathSegment}")
                        val uploadTask = choir.localThumbnail?.let { fileReference.putFile(it) }
                        uploadTask?.addOnProgressListener {
                            val progress = (100 * it.bytesTransferred / it.totalByteCount).toDouble()
                            state.invoke(ResultAPI.Loading(progress))
                        }?.addOnSuccessListener { task ->
                            task.storage.downloadUrl.addOnSuccessListener { url ->
                                documentReference.document(documentData.id).set(choir.copy(localThumbnail = url)).addOnSuccessListener {
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

    }


}