package com.ziss.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ziss.core.databinding.MovieTileItemBinding
import com.ziss.core.domain.entities.Movie
import com.ziss.core.utils.Constants
import com.ziss.core.utils.MovieDIffCallback
import com.ziss.core.utils.loadImage

class MovieTileAdapter : RecyclerView.Adapter<MovieTileAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private val movies = arrayListOf<Movie>()

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

    inner class ListViewHolder(private val binding: MovieTileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.ivMovie.loadImage("${Constants.IMAGE_BASE_URL}${movie.posterPath}")
            binding.tvTitle.text = movie.title
            binding.tvGenre.text = movie.genre.joinToString(", ") { it.name }
            binding.tvRating.text = movie.voteAverage.toString()
            binding.tvReleaseYear.text = movie.releaseDate
            itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
        }
    }

    override

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
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

    fun setMovies(movies: List<Movie>) {
        val diffCallback = MovieDIffCallback(this.movies, movies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.movies.clear()
        this.movies.addAll(movies)

        diffResult.dispatchUpdatesTo(this)
    }
}