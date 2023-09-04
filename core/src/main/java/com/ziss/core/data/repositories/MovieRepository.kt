package com.ziss.core.data.repositories

import com.ziss.core.data.datasources.local.MovieLocalDataSource
import com.ziss.core.data.datasources.remote.MovieRemoteDataSource
import com.ziss.core.domain.entities.Movie
import com.ziss.core.domain.repositories.IMovieRepository
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.mapResult
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) :
    IMovieRepository {
    override fun getTopRatedMovies() = remoteDataSource.getTopRatedMovies().mapResult { movies ->
        movies.map { DataMapper.toMovie(it) }
    }

    override fun getNowPlayingMovies() =
        remoteDataSource.getNowPlayingMovies().mapResult { movies ->
            movies.map { DataMapper.toMovie(it) }
        }

    override fun getWatchlistMovies() = localDataSource.getWatchlistMovies().map { movies ->
        movies.map { DataMapper.toMovie(it) }
    }

    override fun getWatchlistStatus(id: Int) = localDataSource.isWatchlist(id)

    override fun searchMovies(query: String) =
        remoteDataSource.searchMovies(query).mapResult { genres ->
            genres.map { DataMapper.toMovie(it) }
        }

    override suspend fun addWatchlistMovie(movie: Movie) =
        localDataSource.addMovie(DataMapper.toMovieEntity(movie))

    override suspend fun removeWatchlistMovie(id: Int) = localDataSource.removeMovie(id)
}