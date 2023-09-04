package com.ziss.movieapp.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.Constants
import com.ziss.core.utils.loadImage
import com.ziss.movieapp.R
import com.ziss.movieapp.databinding.ActivityDetailBinding
import com.ziss.movieapp.presentation.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var menu: Menu? = null
    private var movie: MovieModel? = null
    private var isWatchlist = 0

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        getMovieData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu

        checkWatchlist()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.watchlist_action -> {
                if (movie != null) {
                    Log.d("Watchlist", isWatchlist.toString())

                    if (isWatchlist == 1) {
                        detailViewModel.removeWatchlistMovie(movie?.id!!)
                    } else {
                        detailViewModel.addWatchlistMovie(movie!!)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.appbarLayout.toolbar)
        supportActionBar?.title = ""
        binding.appbarLayout.toolbar.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)

        binding.appbarLayout.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun checkWatchlist() {
        detailViewModel.getWatchlistStatus(movie?.id!!).observe(this) { isWatchlist ->
            if (isWatchlist) {
                this.isWatchlist = 1
                menu?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_watchlist_filled)
            } else {
                this.isWatchlist = 0
                menu?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_watchlist_white)
            }
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
            if (!movie?.releaseDate.isNullOrEmpty()) {
                tvReleaseYear.text = movie?.releaseDate?.substring(0, 4)
            } else {
                tvReleaseYear.isVisible = false
            }
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