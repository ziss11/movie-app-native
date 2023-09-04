package com.ziss.core.data.repositories

import com.ziss.core.data.datasources.local.MovieLocalDataSource
import com.ziss.core.data.datasources.local.entities.GenreMovieCrossRef
import com.ziss.core.data.datasources.local.entities.TypeEntity
import com.ziss.core.data.datasources.remote.MovieRemoteDataSource
import com.ziss.core.data.datasources.remote.responses.GenreResponse
import com.ziss.core.data.datasources.remote.responses.MovieResponse
import com.ziss.core.domain.entities.Genre
import com.ziss.core.domain.entities.Movie
import com.ziss.core.domain.repositories.IMovieRepository
import com.ziss.core.utils.DataMapper
import com.ziss.core.utils.NetworkBoundResource
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
            override fun loadFromDB() = localDataSource.getTopRatedMovies().map { movies ->
                movies.map { DataMapper.toMovie(it) }
            }

            override suspend fun createCall() = remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val type = TypeEntity(0, "Top Rated")

                val genreMovieCrossRef = data.map { movie ->
                    movie.genreIds.map { genre ->
                        GenreMovieCrossRef(mId = movie.id, gId = genre)
                    }
                }.flatten()

                val movies = data.map { DataMapper.toMovieEntity(it, type.id) }

                localDataSource.insertGenreMovieCrossRef(genreMovieCrossRef)
                localDataSource.insertMovieType(type)
                localDataSource.insertTopRatedMovies(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

        }.toFlow()

    override fun getNowPlayingMovies() =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB() = localDataSource.getNowPlayingMovies().map { movies ->
                movies.map { DataMapper.toMovie(it) }
            }

            override suspend fun createCall() = remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val type = TypeEntity(1, "Now Playing")

                val genreMovieCrossRef = data.map { movie ->
                    movie.genreIds.map { genre ->
                        GenreMovieCrossRef(mId = movie.id, gId = genre)
                    }
                }.flatten()

                val movies = data.map { DataMapper.toMovieEntity(it, type.id) }

                localDataSource.insertGenreMovieCrossRef(genreMovieCrossRef)
                localDataSource.insertMovieType(type)
                localDataSource.insertNowPlayingMovies(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

        }.toFlow()

    override fun getMoviesGenre() =
        object : NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override fun loadFromDB() = localDataSource.getMoviesGenre().map { movies ->
                movies.map { DataMapper.toGenre(it) }
            }

            override suspend fun createCall() = remoteDataSource.getMoviesGenre()

            override suspend fun saveCallResult(data: List<GenreResponse>) =
                localDataSource.insertMoviesGenre(data.map { DataMapper.toGenreEntity(it) })

            override fun shouldFetch(data: List<Genre>?): Boolean = data.isNullOrEmpty()

        }.toFlow()

    override fun getWatchlistMovies() = localDataSource.getWatchlistMovies().map { movies ->
        movies.map { DataMapper.toMovie(it) }
    }

    override fun getWatchlistStatus(id: Int) = localDataSource.isWatchlist(id)

    override suspend fun setWatchlistMovie(id: Int, isWatchlist: Int) {
        localDataSource.setWatchlistMovie(id, isWatchlist)
    }
}