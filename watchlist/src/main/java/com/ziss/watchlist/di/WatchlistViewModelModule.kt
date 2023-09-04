package com.ziss.watchlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ziss.watchlist.presentation.viewmodels.ViewModelFactory
import com.ziss.watchlist.presentation.viewmodels.WatchlistViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class WatchlistViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WatchlistViewModel::class)
    internal abstract fun watchlistViewModel(viewModel: WatchlistViewModel): ViewModel
}