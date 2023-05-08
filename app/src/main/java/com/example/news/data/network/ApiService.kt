package com.example.news.data.network

import com.example.news.data.models.NewsInfoContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadLinesNews(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_SOURCES) sources: String = SOURCES,
    ): NewsInfoContainerDto

    companion object {
        private const val QUERY_API_KEY = "apiKey"
        private const val QUERY_SOURCES = "sources"

        private const val API_KEY = "ab11d66231874d9dbe225be60f3e1ba4"
        private const val SOURCES = "techcrunch"
    }
}