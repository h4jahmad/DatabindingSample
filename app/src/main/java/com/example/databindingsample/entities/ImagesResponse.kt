package com.example.databindingsample.entities

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<Hits>
)

