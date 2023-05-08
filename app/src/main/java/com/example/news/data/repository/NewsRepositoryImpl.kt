package com.example.news.data.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.news.data.database.AppDatabase
import com.example.news.data.mapper.NewsMapper
import com.example.news.data.network.ApiFactory
import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.delay
import androidx.lifecycle.Transformations


class NewsRepositoryImpl(
    private val application: Application
) : NewsRepository {
    private val newsInfoDao = AppDatabase.getInstance(application).newsInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = NewsMapper()
    override fun getTopHeadLinesNews(): LiveData<List<NewsEntity>> {
        return Transformations.map(newsInfoDao.getTopNewsList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadTopHeadlinesNewsData() {
        while (true) {
            try {
                val topNews = apiService.getTopHeadLinesNews()
                val dbModelList = topNews.newsInfoDto.map { mapper.mapDtoToDbModel(it) }
                Log.d("API_RESPONSE", topNews.toString())
                newsInfoDao.insertNewsList(dbModelList)
            } catch (e: Exception) {
                Log.d("ERROR_INT", e.message.toString())
            }
            delay(10000)
        }
    }
}