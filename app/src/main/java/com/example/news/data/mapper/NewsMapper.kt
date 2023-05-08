package com.example.news.data.mapper

import com.example.news.data.database.models.NewsInfoDbModel
import com.example.news.data.models.NewsInfoDto
import com.example.news.domain.models.NewsEntity


class NewsMapper {

    // посмотреть тут еще

    fun mapDtoToDbModel(dto: NewsInfoDto) = NewsInfoDbModel(
        id = 0,
        author = dto.author,
        title = dto.title,
        description = dto.description,
        url = dto.url,
        urlToImage = dto.urlToImage,
        publishedAt = dto.publishedAt,
        content = dto.content
    )

    fun mapDbModelToEntity(model: NewsInfoDbModel) = NewsEntity(
        id = model.id,
        author = model.author,
        title = model.title,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = model.publishedAt,
        content = model.content
    )

}

