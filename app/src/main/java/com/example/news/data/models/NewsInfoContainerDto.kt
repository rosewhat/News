package com.example.news.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsInfoContainerDto(
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,
    @SerializedName("articles")
    @Expose
    val newsInfoDto: List<NewsInfoDto>,
)