package com.example.news.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.database.models.NewsInfoDbModel
import com.example.news.data.database.models.NewsLikeInfoDbModel

@Dao
interface NewsInfoDao {

    @Query("SELECT * FROM full_news ORDER BY publishedAt")
    fun getTopNewsList(): LiveData<List<NewsInfoDbModel>>

    @Query("SELECT * FROM full_news WHERE author == :author LIMIT 1")
    fun getDetailTopNews(author: String): LiveData<NewsInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(priceList: List<NewsInfoDbModel>)

    @Delete
    suspend fun deleteNews(newsInfoDbModel: NewsInfoDbModel)

    @Query("SELECT * FROM like_news ORDER BY publishedAt")
    fun getLikeNewsList(): LiveData<List<NewsLikeInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikeNews(likeItem: NewsLikeInfoDbModel)

    @Delete
    suspend fun deleteLikeNews(newsLikeInfoDbModel: NewsLikeInfoDbModel)
}