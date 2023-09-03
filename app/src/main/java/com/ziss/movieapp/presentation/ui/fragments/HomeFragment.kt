package com.ziss.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.core.domain.entities.Movie
import com.ziss.core.presentation.adapter.MovieCardAdapter
import com.ziss.core.presentation.adapter.MovieTileAdapter
import com.ziss.core.utils.MarginItemDecoration
import com.ziss.core.utils.ResultState
import com.ziss.movieapp.databinding.FragmentHomeBinding
import com.ziss.movieapp.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        setTopRatedAdapter()
        setNowPlayingAdapter()
    }

    private fun setToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = ""
    }

    private fun setTopRatedAdapter() {
        val layout = LinearLayoutManager(
            requireActivity(), LinearLayoutManager.HORIZONTAL,
            false
        )
        val decoration = MarginItemDecoration(16)

        movieCardAdapter = MovieCardAdapter()
        movieCardAdapter.setOnItemClicked(object : MovieCardAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
            }
        })

        binding.rvTopRated.apply {
            adapter = movieCardAdapter
            layoutManager = layout
            addItemDecoration(decoration)
        }

        fetchTopRatedMovies()
    }

    private fun setNowPlayingAdapter() {
        val layout = object : LinearLayoutManager(requireActivity()) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        movieTileAdapter = MovieTileAdapter()
        movieTileAdapter.setOnItemClicked(object : MovieTileAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
            }
        })

        binding.rvPopular.apply {
            adapter = movieTileAdapter
            layoutManager = layout
        }

        fetchNowPlayingMovies()
    }

    private fun fetchTopRatedMovies() {
        homeViewModel.getTopRatedMovies().observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> {}

                is ResultState.Success -> {
                    val movies = result.data

                    if (!movies.isNullOrEmpty()) {
                        movieCardAdapter.setMovies(movies)
                    }
                }

                is ResultState.Failed -> {}
            }
        }
    }

    private fun fetchNowPlayingMovies() {
        homeViewModel.getNowPlayingMovies().observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> {}

                is ResultState.Success -> {
                    val movies = result.data

                    if (!movies.isNullOrEmpty()) {
                        movieTileAdapter.setMovies(movies)
                    }
                }

                is ResultState.Failed -> {}
            }
        }
    }
}