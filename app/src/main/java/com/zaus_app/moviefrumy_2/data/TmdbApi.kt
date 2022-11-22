package com.zaus_app.moviefrumy_2.data

import com.zaus_app.moviefrumy_20.data.entity.TmdbResultsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    suspend fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TmdbResultsDto>
}