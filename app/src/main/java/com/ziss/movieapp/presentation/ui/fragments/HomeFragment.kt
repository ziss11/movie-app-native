package com.ziss.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.core.presentation.adapter.MovieCardAdapter
import com.ziss.core.presentation.adapter.MovieTileAdapter
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.MarginItemDecoration
import com.ziss.core.utils.ResultState
import com.ziss.movieapp.databinding.FragmentHomeBinding
import com.ziss.movieapp.presentation.ui.activities.DetailActivity
import com.ziss.movieapp.presentation.ui.activities.SearchActivity
import com.ziss.movieapp.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var movieTileAdapter: MovieTileAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        fetchTopRatedMovies()
        fetchNowPlayingMovies()

        binding?.searchLayout?.edSearch?.setOnClickListener {
            SearchActivity.start(requireActivity())
        }
    }

    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding?.appbarLayout?.toolbar)
        activity.supportActionBar?.title = ""
    }

    private fun setTopRatedAdapter(movies: List<MovieModel>) {
        val layout = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.HORIZONTAL,
            false
        )
        val decoration = MarginItemDecoration(16)

        movieCardAdapter = MovieCardAdapter()
        movieCardAdapter.setOnItemClicked(object : MovieCardAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieModel) {
                DetailActivity.start(requireActivity(), movie)
            }
        })

        binding?.rvTopRated?.apply {
            adapter = movieCardAdapter
            layoutManager = layout
            addItemDecoration(decoration)
        }

        movieCardAdapter.setMovies(movies)
    }

    private fun setNowPlayingAdapter(movies: List<MovieModel>) {
        val layout = object : LinearLayoutManager(requireActivity()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        movieTileAdapter = MovieTileAdapter()
        movieTileAdapter.setOnItemClicked(object : MovieTileAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieModel) {
                DetailActivity.start(requireActivity(), movie)
            }
        })

        binding?.rvPopular?.apply {
            adapter = movieTileAdapter
            layoutManager = layout
        }

        movieTileAdapter.setMovies(movies)
    }


    private fun fetchTopRatedMovies() {
        homeViewModel.getTopRatedMovies().observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> showTopRatedProgressBar()

                is ResultState.Success -> {
                    showTopRatedProgressBar(false)
                    val movies = result.data

                    if (movies.isNotEmpty()) {
                        setTopRatedAdapter(movies)
                    } else {
                        showTopRatedMessage()
                    }
                }

                is ResultState.Failed -> {
                    showTopRatedProgressBar(false)
                    showTopRatedMessage()
                }
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        homeViewModel.getNowPlayingMovies().observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> showPopularProgressBar()

                is ResultState.Success -> {
                    showPopularProgressBar(false)
                    val movies = result.data

                    if (movies.isNotEmpty()) {
                        setNowPlayingAdapter(movies)
                    } else {
                        showPopularMessage()
                    }
                }

                is ResultState.Failed -> {
                    showPopularProgressBar(false)
                    showPopularMessage()
                }
            }
        }
    }

    private fun showTopRatedProgressBar(isLoading: Boolean = true) {
        binding?.trProgressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showPopularProgressBar(isLoading: Boolean = true) {
        binding?.popularProgressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showTopRatedMessage(isShow: Boolean = true) {
        if (isShow) {
            binding?.tvTrMessage?.visibility = View.VISIBLE
            binding?.rvTopRated?.visibility = View.INVISIBLE
        } else {
            binding?.tvTrMessage?.visibility = View.GONE
            binding?.rvTopRated?.visibility = View.VISIBLE
        }
    }

    private fun showPopularMessage(isShow: Boolean = true) {
        if (isShow) {
            binding?.tvPopularMessage?.visibility = View.VISIBLE
            binding?.rvPopular?.visibility = View.INVISIBLE
        } else {
            binding?.tvPopularMessage?.visibility = View.GONE
            binding?.rvPopular?.visibility = View.VISIBLE
        }
    }
}