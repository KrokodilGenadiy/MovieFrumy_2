package com.zaus_app.moviefrumy_2.domain

import com.zaus_app.moviefrumy_2.data.API
import com.zaus_app.moviefrumy_2.data.TmdbApi
import com.zaus_app.moviefrumy_2.utils.Converter
import com.zaus_app.moviefrumy_2.view.fragments.home_fragment.HomeFragmentViewModel
import com.zaus_app.moviefrumy_20.data.entity.TmdbResultsDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val retrofitService: TmdbApi) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}