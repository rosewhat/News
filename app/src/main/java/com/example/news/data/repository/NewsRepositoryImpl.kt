package com.example.news.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.news.data.database.AppDatabase
import com.example.news.data.mapper.NewsMapper
import com.example.news.domain.models.NewsEntity
import com.example.news.domain.repository.NewsRepository
import com.example.news.workers.RefreshDataWorker


class NewsRepositoryImpl(
    private val application: Application
) : NewsRepository {
    private val newsInfoDao = AppDatabase.getInstance(application).newsInfoDao()
    private val mapper = NewsMapper()
    override fun getTopHeadLinesNews(): LiveData<List<NewsEntity>> {
        return Transformations.map(newsInfoDao.getTopNewsList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getDetailTopHeadlinesNewsUseCase(author: String): LiveData<NewsEntity> {
        return Transformations.map(newsInfoDao.getDetailTopNews(author = author)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadTopHeadlinesNewsData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

    override suspend fun deleteChoiceNewsFromListUseCase(newsEntity: NewsEntity) {
        newsInfoDao.deleteNews(mapper.mapEntityToDbModel(newsEntity))
    }
}
