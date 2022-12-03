package com.zaus_app.moviefrumy_2.view.fragments.home_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.zaus_app.moviefrumy_2.data.paging.FilmsRemoteDataSource
import com.zaus_app.moviefrumy_2.App
import com.zaus_app.moviefrumy_2.data.entity.Film
import com.zaus_app.moviefrumy_2.data.paging.FilmsRemoteDataSourceImpl
import com.zaus_app.moviefrumy_2.domain.Interactor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    private val filmsRemoteDataSource: FilmsRemoteDataSource

    init {
        App.instance.dagger.inject(this)
        filmsRemoteDataSource = FilmsRemoteDataSourceImpl(interactor)
    }

    fun getMovies(): Flow<PagingData<Film>> {
        return filmsRemoteDataSource.getMovies()
            .map { pagingData ->
                pagingData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }
}