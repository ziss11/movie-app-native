package com.ziss.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object MovieTypeUtils {
    fun getMovieTypeQuery(movieType: MovieType): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM movie JOIN type ON type.id=movie.typeId ")
        when (movieType) {
            MovieType.TOP_RATED -> simpleQuery.append("WHERE movie.typeId=0 OR type.id=0")
            MovieType.NOW_PLAYING -> simpleQuery.append("WHERE movie.typeId=1 OR type.id=1")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}