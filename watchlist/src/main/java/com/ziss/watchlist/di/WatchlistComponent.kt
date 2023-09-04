package com.ziss.watchlist.di

import android.content.Context
import com.ziss.movieapp.di.WatchlistDependencies
import com.ziss.watchlist.presentation.ui.WatchlistFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [WatchlistDependencies::class],
    modules = [WatchlistViewModelModule::class]
)
interface WatchlistComponent {
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun dependencies(component: WatchlistDependencies): Builder
        fun build(): WatchlistComponent
    }

    fun inject(fragment: WatchlistFragment)
}
