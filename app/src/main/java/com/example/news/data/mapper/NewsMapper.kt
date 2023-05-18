package com.example.news.data.mapper

import com.example.news.data.database.models.NewsInfoDbModel
import com.example.news.data.database.models.NewsLikeInfoDbModel
import com.example.news.data.models.NewsInfoDto
import com.example.news.domain.models.NewsEntity
import java.text.SimpleDateFormat
import java.util.Locale


class NewsMapper {
    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        author = dto.author,
        title = dto.title,
        description = dto.description,
        url = dto.url,
        urlToImage = dto.urlToImage,
        publishedAt = dto.publishedAt,
        content = dto.content
    )

    fun mapDbModelToEntity(model: NewsInfoDbModel) = NewsEntity(
        author = model.author,
        title = model.title,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = convertTime(model.publishedAt),
        content = model.content
    )

    fun mapEntityToDbModel(newsEntity: NewsEntity) = NewsInfoDbModel(
        author = newsEntity.author,
        title = newsEntity.title,
        description = newsEntity.description,
        url = newsEntity.url,
        urlToImage = newsEntity.urlToImage,
        publishedAt = newsEntity.publishedAt,
        content = newsEntity.content
    )

    fun mapLikeEntityToDbModel(newsEntity: NewsEntity) = NewsLikeInfoDbModel(
        author = newsEntity.author,
        title = newsEntity.title,
        description = newsEntity.description,
        url = newsEntity.url,
        urlToImage = newsEntity.urlToImage,
        publishedAt = newsEntity.publishedAt,
        content = newsEntity.content
    )

    fun mapDbModelToLikeEntity(model: NewsLikeInfoDbModel) = NewsEntity(
        author = model.author,
        title = model.title,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = model.publishedAt,
        content = model.content
    )


    private fun convertTime(published: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = dateFormat.parse(published)
        val outputFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
        return outputFormat.format(date ?: "No Time")
    }


}

