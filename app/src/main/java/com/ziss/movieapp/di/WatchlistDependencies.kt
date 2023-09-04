package com.ziss.movieapp.di

import com.ziss.core.domain.usecases.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WatchlistDependencies {
    fun provideMovieUseCase(): MovieUseCase
}