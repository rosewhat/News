package com.example.news.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.news.data.database.AppDatabase
import com.example.news.data.mapper.NewsMapper
import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsLikeRepository

class NewsLikeRepositoryImpl(
    application: Application
) : NewsLikeRepository {

    private val newsInfoDao = AppDatabase.getInstance(application).newsInfoDao()
    private val mapper = NewsMapper()

    override suspend fun insertLikeNews(newsEntity: NewsEntity) {
        newsInfoDao.insertLikeNews(mapper.mapLikeEntityToDbModel(newsEntity))
    }

    override suspend fun deleteChoiceLikeNewsFromListUseCase(newsEntity: NewsEntity) {
        newsInfoDao.deleteLikeNews(mapper.mapLikeEntityToDbModel(newsEntity))
    }

    override fun getLikeNewsUseCase(): LiveData<List<NewsEntity>> {
        return Transformations.map(newsInfoDao.getLikeNewsList()) {
            it.map {
                mapper.mapDbModelToLikeEntity(it)
            }
        }
    }
}