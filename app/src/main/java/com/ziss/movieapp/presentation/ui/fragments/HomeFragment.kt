package com.ziss.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
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
    private val binding get() = _binding!!

    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var movieTileAdapter: MovieTileAdapter

    private lateinit var topRatedObserver: LiveData<ResultState<List<MovieModel>>>
    private lateinit var nowPlayingObserver: LiveData<ResultState<List<MovieModel>>>

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchLayout.edSearch.setOnClickListener {
            SearchActivity.start(requireActivity())
        }

        setNowPlayingAdapter()
        setTopRatedAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        topRatedObserver.removeObservers(this)
        nowPlayingObserver.removeObservers(this)

        binding.rvTopRated.adapter = null
        binding.rvNowPlaying.adapter = null

        _binding = null
    }

    private fun setTopRatedAdapter() {
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
            override fun onItemClicked(movie: MovieModel) {
                DetailActivity.start(requireActivity(), movie)
            }
        })

        binding.rvNowPlaying.apply {
            adapter = movieTileAdapter
            layoutManager = layout
        }

        fetchNowPlayingMovies()
    }


    private fun fetchTopRatedMovies() {
        topRatedObserver = homeViewModel.getTopRatedMovies()
        topRatedObserver.observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> showTopRatedProgressBar()

                is ResultState.Success -> {
                    showTopRatedProgressBar(false)
                    val movies = result.data

                    if (movies.isNotEmpty()) {
                        movieCardAdapter.setMovies(movies)
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
        nowPlayingObserver = homeViewModel.getNowPlayingMovies()
        nowPlayingObserver.observe(requireActivity()) { result ->
            when (result) {
                is ResultState.Loading -> showPopularProgressBar()

                is ResultState.Success -> {
                    showPopularProgressBar(false)
                    val movies = result.data

                    if (movies.isNotEmpty()) {
                        movieTileAdapter.setMovies(movies)
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
        binding.trProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showPopularProgressBar(isLoading: Boolean = true) {
        binding.popularProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showTopRatedMessage(isShow: Boolean = true) {
        if (isShow) {
            binding.tvTrMessage.visibility = View.VISIBLE
            binding.rvTopRated.visibility = View.INVISIBLE
        } else {
            binding.tvTrMessage.visibility = View.GONE
            binding.rvTopRated.visibility = View.VISIBLE
        }
    }

    private fun showPopularMessage(isShow: Boolean = true) {
        if (isShow) {
            binding.tvPopularMessage.visibility = View.VISIBLE
            binding.rvNowPlaying.visibility = View.INVISIBLE
        } else {
            binding.tvPopularMessage.visibility = View.GONE
            binding.rvNowPlaying.visibility = View.VISIBLE
        }
    }
}