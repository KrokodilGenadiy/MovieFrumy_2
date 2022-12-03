package com.zaus_app.moviefrumy_2.di.modules

import com.zaus_app.moviefrumy_2.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()
}