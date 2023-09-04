package com.ziss.core.utils


sealed class ResultState<out R> {
    class Success<T>(val data: T) : ResultState<T>()
    class Failed(val message: String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()

}