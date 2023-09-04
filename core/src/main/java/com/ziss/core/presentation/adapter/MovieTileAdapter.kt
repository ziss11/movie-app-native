package com.ziss.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ziss.core.databinding.MovieTileItemBinding
import com.ziss.core.presentation.models.MovieModel
import com.ziss.core.utils.Constants
import com.ziss.core.utils.MovieDIffCallback
import com.ziss.core.utils.loadImage

class MovieTileAdapter : RecyclerView.Adapter<MovieTileAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private val movies = arrayListOf<MovieModel>()

    interface OnItemClickCallback {
        fun onItemClicked(movie: MovieModel)
    }

    inner class ListViewHolder(private val binding: MovieTileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            binding.apply {
                ivMovie.loadImage("${Constants.IMAGE_BASE_URL}${movie.posterPath}")
                tvTitle.text = movie.title
                tvRating.text = movie.voteAverage.toString()
                if (!movie.releaseDate.isNullOrEmpty()) {
                    tvReleaseYear.text = movie.releaseDate.substring(0, 4)
                } else {
                    tvReleaseYear.isVisible = false
                }
                itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            MovieTileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun setOnItemClicked(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieModel>) {
        val diffCallback = MovieDIffCallback(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.movies.clear()
        this.movies.addAll(movies)

        diffResult.dispatchUpdatesTo(this)
    }
}