package com.christopher_elias.features.movies.data.data_source

import androidx.paging.PagingData
import com.zaus_app.moviefrumy_2.domain.Film
import kotlinx.coroutines.flow.Flow

interface FilmsRemoteDataSource {
    fun getMovies(): Flow<PagingData<Film>>
}