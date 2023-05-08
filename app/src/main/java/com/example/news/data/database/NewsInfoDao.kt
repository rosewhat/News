package com.example.news.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.database.models.NewsInfoDbModel

@Dao
interface NewsInfoDao {

    @Query("SELECT * FROM full_news ORDER BY publishedAt")
    fun getTopNewsList() : LiveData<List<NewsInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(priceList: List<NewsInfoDbModel>)

    // TODO: add method delete



}