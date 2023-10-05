package com.luigidev.himnosycorosmiepiadmin.home.domain.models

data class Choir(
    val id: String,
    val choirNumber: Int,
    val title: String,
    val lyrics: String,
    val thumbnail: String?,
    val storagePath: String?
){
    fun doesMatchSearchQuery(query: String): Boolean{
        val matchingCombinations = listOf(
            "${title.first()}",
            "$title $lyrics",
            "$title $choirNumber",
            "$title$choirNumber",
            "${title.first()} ${lyrics.first()}"
        )

        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
