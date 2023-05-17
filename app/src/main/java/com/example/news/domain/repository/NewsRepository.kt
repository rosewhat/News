package com.example.news.domain.repository

import androidx.lifecycle.LiveData
import com.example.news.domain.models.NewsEntity

interface NewsRepository {

    fun getTopHeadLinesNews(): LiveData<List<NewsEntity>>

    fun getDetailTopHeadlinesNewsUseCase(author: String): LiveData<NewsEntity>
     fun loadTopHeadlinesNewsData()

    suspend fun deleteChoiceNewsFromListUseCase(newsEntity: NewsEntity)

}