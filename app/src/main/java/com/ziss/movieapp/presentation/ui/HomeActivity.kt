package com.ziss.movieapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.core.domain.entities.Movie
import com.ziss.core.presentation.adapter.MovieAdapter
import com.ziss.core.presentation.adapter.MovieAdapter.OnItemClickCallback
import com.ziss.core.utils.MarginItemDecoration
import com.ziss.core.utils.ResultState
import com.ziss.movieapp.R
import com.ziss.movieapp.databinding.ActivityHomeBinding
import com.ziss.movieapp.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var movieAdapter: MovieAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setListAdapter()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.tvToolbarTitle.text = getString(R.string.home_title)
    }

    private fun setListAdapter() {
        val layout = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )
        val decoration = MarginItemDecoration(16)

        movieAdapter = MovieAdapter()
        movieAdapter.setOnItemClicked(object : OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
            }
        })

        binding.rvTopRated.apply {
            adapter = movieAdapter
            layoutManager = layout
            addItemDecoration(decoration)
        }

        fetchTopRatedMovies()
    }

    private fun fetchTopRatedMovies() {
        homeViewModel.getTopRatedMovies().observe(this) { result ->
            when (result) {
                is ResultState.Loading -> {}

                is ResultState.Success -> {
                    val movies = result.data

                    if (movies != null && !movies.isEmpty()) {
                        Log.d("MovieResult", movies.toString())
                        movieAdapter.setMovies(movies)
                    }
                }

                is ResultState.Failed -> {}
            }
        }
    }
}