package com.ziss.core.data.repositories

import com.ziss.core.data.datasources.local.MovieLocalDataSource
import com.ziss.core.data.datasources.local.entities.TypeEntity
import com.ziss.core.data.datasources.remote.MovieRemoteDataSource
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Movie
import com.ziss.core.domain.repositories.IMovieRepository
import com.ziss.core.utils.NetworkBoundResource
import com.ziss.core.utils.toMovieEntityList
import com.ziss.core.utils.toMovieList
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) :
    IMovieRepository {
    override fun getTopRatedMovies() =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB() = localDataSource.getTopRatedMovies().map {
                it.toMovieList()
            }

            override suspend fun createCall() = remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val type = TypeEntity(0, "Top Rated")
                localDataSource.insertMovieType(type)
                localDataSource.insertTopRatedMovies(data.toMovieEntityList(type.id))
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

        }.toFlow()

    override fun getNowPlayingMovies() =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB() = localDataSource.getNowPlayingMovies().map {
                it.toMovieList()
            }

            override suspend fun createCall() = remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val type = TypeEntity(1, "Now Playing")
                localDataSource.insertMovieType(type)
                localDataSource.insertNowPlayingMovies(data.toMovieEntityList(type.id))
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

        }.toFlow()
}