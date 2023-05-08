package com.example.news.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsNameDto(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
)