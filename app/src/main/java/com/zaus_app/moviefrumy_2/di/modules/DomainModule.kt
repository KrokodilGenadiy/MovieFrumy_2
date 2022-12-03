package com.zaus_app.moviefrumy_2.di.modules

import com.zaus_app.moviefrumy_2.data.TmdbApi
import com.zaus_app.moviefrumy_2.domain.Interactor
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(tmdbApi: TmdbApi) = Interactor(tmdbApi)
}