package com.example.news.domain.repository

import androidx.lifecycle.LiveData
import com.example.news.domain.models.NewsEntity


interface NewsLikeRepository {


    suspend fun insertLikeNews(newsEntity: NewsEntity)

    suspend fun deleteChoiceLikeNewsFromListUseCase(newsEntity: NewsEntity)

    fun getLikeNewsUseCase(): LiveData<List<NewsEntity>>


}