package com.ziss.movieapp.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.core.presentation.adapter.MovieTileAdapter
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.ResultState
import com.ziss.movieapp.R
import com.ziss.movieapp.databinding.ActivitySearchBinding
import com.ziss.movieapp.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var movieAdapter: MovieTileAdapter

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        binding.searchLayout.edSearch.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                searchViewModel.query.value = text.toString()
            }
        }

        setSearchMovieAdapter()
    }

    private fun setToolbar() {
        binding.appbarLayout.toolbar.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)

        binding.appbarLayout.toolbar.setNavigationOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            onBackPressed()
        }
    }

    private fun setSearchMovieAdapter() {
        val layout = LinearLayoutManager(this)

        movieAdapter = MovieTileAdapter()
        movieAdapter.setOnItemClicked(object : MovieTileAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieModel) {
                DetailActivity.start(this@SearchActivity, movie)
            }
        })

        binding.rvSearch.apply {
            adapter = movieAdapter
            layoutManager = layout
        }

        fetchSearchedMovies()
    }

    private fun fetchSearchedMovies() {
        searchViewModel.searchResult.observe(this) { result ->
            when (result) {
                is ResultState.Loading -> showProgressBar()

                is ResultState.Success -> {
                    showProgressBar(false)
                    val movies = result.data

                    if (movies.isNotEmpty()) {
                        movieAdapter.setMovies(movies)
                    } else {
                        showMessage()
                    }
                }

                is ResultState.Failed -> {
                    showProgressBar(false)
                    showMessage()
                }
            }
        }
    }

    private fun showProgressBar(isLoading: Boolean = true) {
        if (isLoading) {
            binding.popularProgressBar.visibility = View.VISIBLE
            binding.rvSearch.visibility = View.INVISIBLE
        } else {
            binding.popularProgressBar.visibility = View.GONE
            binding.rvSearch.visibility = View.VISIBLE

        }
    }

    private fun showMessage(isShow: Boolean = true) {
        if (isShow) {
            binding.emptyState.visibility = View.VISIBLE
            binding.rvSearch.visibility = View.INVISIBLE
        } else {
            binding.emptyState.visibility = View.GONE
            binding.rvSearch.visibility = View.VISIBLE
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, SearchActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}