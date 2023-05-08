package com.example.news.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.usecases.GetTopHeadLinesNewsUseCase
import com.example.news.domain.usecases.LoadTopHeadlinesNewsDataUseCase
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NewsRepositoryImpl(application)

   private val getTopHeadLinesNewsUseCase = GetTopHeadLinesNewsUseCase(repository)

  private val loadTopHeadlinesNewsDataUseCase = LoadTopHeadlinesNewsDataUseCase(repository)

    init {
        viewModelScope.launch {
            loadTopHeadlinesNewsDataUseCase()
        }
    }
}