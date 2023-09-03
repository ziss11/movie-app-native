package com.ziss.movieapp.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.Constants
import com.ziss.core.utils.loadImage
import com.ziss.movieapp.R
import com.ziss.movieapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var movie: MovieModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        getMovieData()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.appbarLayout.toolbar)
        supportActionBar?.title = ""
        binding.toolbarTitle.text = getString(R.string.detail)
        binding.appbarLayout.toolbar.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)

        binding.appbarLayout.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun getMovieData() {
        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MOVIE_EXTRA, MovieModel::class.java)

        } else {
            intent.getParcelableExtra(MOVIE_EXTRA)
        }

        binding.apply {
            ivMovieBackdrop.loadImage("${Constants.IMAGE_BASE_URL}${movie?.backdropPath}")
            movieCard.ivMovie.loadImage("${Constants.IMAGE_BASE_URL}${movie?.posterPath}")
            tvRating.text = movie?.voteAverage.toString()
            tvMovieTitle.text = movie?.title
            tvReleaseYear.text = movie?.releaseDate?.substring(0, 4)
            tvGenre.text = movie?.genres?.joinToString(", ") { it.name }
            tvOverview.text = movie?.overview
        }
    }

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"

        fun start(context: Context, movie: MovieModel) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra(MOVIE_EXTRA, movie)
                context.startActivity(this)
            }
        }
    }
}