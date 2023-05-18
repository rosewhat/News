package com.example.news.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.NewsLikeRepositoryImpl
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.models.NewsEntity
import com.example.news.domain.usecases.DeleteChoiceLikeNewsFromListUseCase
import com.example.news.domain.usecases.GetDetailTopHeadlinesNewsUseCase
import com.example.news.domain.usecases.GetLikeNewsUseCase
import com.example.news.domain.usecases.GetTopHeadLinesNewsUseCase
import com.example.news.domain.usecases.InsertLikeNewsInListUseCase
import com.example.news.domain.usecases.LoadTopHeadlinesNewsDataUseCase
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsRepositoryImpl(application)
    private val likeRepository = NewsLikeRepositoryImpl(application)
    private val getTopHeadLinesNewsUseCase = GetTopHeadLinesNewsUseCase(repository)
    private val getDetailTopHeadlinesNewsUseCase = GetDetailTopHeadlinesNewsUseCase(repository)
    private val loadTopHeadlinesNewsDataUseCase = LoadTopHeadlinesNewsDataUseCase(repository)
    private val insertLikeNewsInListUseCase = InsertLikeNewsInListUseCase(likeRepository)
    private val getLikeNewsInListUseCase = GetLikeNewsUseCase(likeRepository)
    private val deleteChoiceLikeNewsFromListUseCase =
        DeleteChoiceLikeNewsFromListUseCase(likeRepository)


    val newsInfoList = getTopHeadLinesNewsUseCase()

    val newsLikeInfoList = getLikeNewsInListUseCase()

    init {
        loadTopHeadlinesNewsDataUseCase()
    }

    fun getDetailNewsInfo(author: String) = getDetailTopHeadlinesNewsUseCase(author = author)

    fun deleteChoiceNewsFromListUseCase(news: NewsEntity) {
        viewModelScope.launch {
           repository.deleteChoiceNewsFromListUseCase(news)
        }
    }

    fun insertLikeNews(news: NewsEntity) {
        viewModelScope.launch {
            insertLikeNewsInListUseCase(news)
        }
    }

    fun deleteChoiceLikeNewsFromList(newsEntity: NewsEntity) {
        viewModelScope.launch {
            likeRepository.deleteChoiceLikeNewsFromListUseCase(newsEntity)
        }
    }




}