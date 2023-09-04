package com.ziss.core.utils

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.ziss.core.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ImageView.loadImage(url: Any?) {
    Glide.with(this.context).load(url).centerCrop().placeholder(R.drawable.ic_load_image)
        .error(R.drawable.ic_broken_image_24).into(this)
}

fun <T, R> Flow<ResultState<T>>.toModelResultLiveData(
    mapper: suspend (T) -> R
): LiveData<ResultState<R>> {
    return this.map { resultState ->
        when (resultState) {
            is ResultState.Success -> {
                val data = mapper(resultState.data!!)
                ResultState.Success(data)
            }

            is ResultState.Failed -> {
                ResultState.Failed(resultState.message.toString())
            }

            is ResultState.Loading -> {
                ResultState.Loading()
            }
        }
    }.asLiveData()
}

fun <T, R> Flow<T>.toModelLiveData(
    mapper: suspend (T) -> R
): LiveData<R> {
    return this.map { mapper(it) }.asLiveData()
}