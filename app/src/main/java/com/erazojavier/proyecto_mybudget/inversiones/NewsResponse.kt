package com.erazojavier.proyecto_mybudget.inversiones

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)