package com.ziss.watchlist.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziss.core.presentation.adapter.MovieTileAdapter
import com.ziss.core.presentation.models.MovieModel
import com.ziss.movieapp.presentation.ui.activities.DetailActivity
import com.ziss.watchlist.databinding.FragmentWatchlistBinding
import com.ziss.watchlist.presentation.viewmodels.ViewModelFactory
import com.ziss.watchlist.presentation.viewmodels.WatchlistViewModel
import com.ziss.watchlist.utils.inject
import javax.inject.Inject

class WatchlistFragment : Fragment() {
    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieTileAdapter: MovieTileAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val watchlistViewModel: WatchlistViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    private fun fetchWatchlistMovies() {
        watchlistViewModel.getWatchlistMovies().observe(requireActivity()) { result ->
            if (result.isEmpty()) {
                showMessage()
            } else {
                movieTileAdapter.setMovies(result)
            }
        }
    }

    private fun setMovieAdapter() {
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

        binding.rvWatchlist.apply {
            adapter = movieTileAdapter
            layoutManager = layout
        }

        fetchWatchlistMovies()
    }

    private fun showMessage(isShow: Boolean = true) {
        binding.emptyState.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}