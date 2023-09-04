package com.ziss.watchlist.utils

import com.ziss.movieapp.di.WatchlistDependencies
import com.ziss.watchlist.di.DaggerWatchlistComponent
import com.ziss.watchlist.presentation.ui.WatchlistFragment
import dagger.hilt.android.EntryPointAccessors

internal fun WatchlistFragment.inject() {
    DaggerWatchlistComponent
        .builder()
        .context(requireContext())
        .dependencies(
            EntryPointAccessors.fromApplication(
                requireContext(),
                WatchlistDependencies::class.java
            )
        )
        .build()
        .inject(this)
}