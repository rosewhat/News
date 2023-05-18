package com.example.news.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.news.data.database.AppDatabase
import com.example.news.data.mapper.NewsMapper
import com.example.news.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParams: WorkerParameters
)  : CoroutineWorker(appContext = context, params = workerParams) {
    private val newsInfoDao = AppDatabase.getInstance(context).newsInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = NewsMapper()
    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topNews = apiService.getTopHeadLinesNews()
                val dbModelList = topNews.newsInfoDto.map { mapper.mapDtoToDbModel(it) }
                newsInfoDao.insertNewsList(dbModelList)
            } catch (e: Exception) {
                Log.d("ERROR_INT", e.message.toString())
            }
            delay(10000)
        }
    }



    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest() : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}