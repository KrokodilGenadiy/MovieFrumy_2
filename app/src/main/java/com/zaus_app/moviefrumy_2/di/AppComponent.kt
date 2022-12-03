package com.zaus_app.moviefrumy_2.di

import com.zaus_app.moviefrumy_2.di.modules.DatabaseModule
import com.zaus_app.moviefrumy_2.di.modules.DomainModule
import com.zaus_app.moviefrumy_2.di.modules.RemoteModule
import com.zaus_app.moviefrumy_2.view.fragments.home_fragment.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)

interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}