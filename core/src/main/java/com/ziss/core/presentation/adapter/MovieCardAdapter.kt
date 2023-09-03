package com.ziss.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ziss.core.databinding.MovieCardItemBinding
import com.ziss.core.domain.entities.Movie
import com.ziss.core.utils.Constants.IMAGE_BASE_URL
import com.ziss.core.utils.MovieDIffCallback
import com.ziss.core.utils.loadImage

class MovieCardAdapter : RecyclerView.Adapter<MovieCardAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private val movies = arrayListOf<Movie>()

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

    inner class ListViewHolder(private val binding: MovieCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.ivMovie.loadImage("$IMAGE_BASE_URL${movie.posterPath}")
            binding.ivMovie.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
        }
    }

    override

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            MovieCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun setOnItemClicked(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<Movie>) {
        val diffCallback = MovieDIffCallback(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.movies.clear()
        this.movies.addAll(movies)

        diffResult.dispatchUpdatesTo(this)
    }
}