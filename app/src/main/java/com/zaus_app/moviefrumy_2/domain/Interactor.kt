package com.zaus_app.moviefrumy_2.domain

import com.zaus_app.moviefrumy_2.data.API
import com.zaus_app.moviefrumy_2.data.TmdbApi
import com.zaus_app.moviefrumy_20.data.entity.TmdbResultsDto
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class Interactor (private val retrofitService: TmdbApi) {
    suspend fun getFilmsFromApi(page: Int): Response<TmdbResultsDto> {
        return retrofitService.getFilms(API.KEY, Locale.getDefault().toLanguageTag(), page)
    }
}