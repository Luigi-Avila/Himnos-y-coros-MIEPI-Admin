package com.luigidev.himnosycorosmiepiadmin.form.data.network

import com.luigidev.himnosycorosmiepiadmin.core.ResultAPI
import com.luigidev.himnosycorosmiepiadmin.form.data.utils.toDomainFillOut
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.form.domain.models.ChoirFillOut
import javax.inject.Inject

class FormService @Inject constructor(private val client: FormClient) {


    fun uploadChoir(
        choir: Choir,
        uploadingState: (ResultAPI<String>) -> Unit
    ) = client.uploadChoir(choir, uploadingState)

    fun uploadChoirWithImage(
        choir: Choir,
        state: (ResultAPI<String>) -> Unit
    ) = client.uploadChoirWithImage(choir, state)

    fun getChoirById(id: String, apiState: (ResultAPI<ChoirFillOut>) -> Unit) {
        client.getChoirById(id) { result ->
            when (result) {
                is ResultAPI.Error -> apiState.invoke(ResultAPI.Error(result.message))
                is ResultAPI.Loading -> apiState.invoke(ResultAPI.Loading(result.progress))
                is ResultAPI.Success -> apiState.invoke(
                    ResultAPI.Success(
                        result.data?.toDomainFillOut()
                            ?: ChoirFillOut()
                    )
                )
            }
        }
    }

    fun updateChoir(
        id: String,
        choir: Choir,
        apiState: (ResultAPI<String>) -> Unit
    ) = client.updateChoir(id, choir, apiState)

    fun updateChoirWithImage(
        id: String,
        choir: Choir,
        apiState: (ResultAPI<String>) -> Unit
    ) = client.updateChoirWithImage(id, choir, apiState)

}