package com.ziss.core.utils

sealed class ApiState<out R> {
    class Success<T>(val data: T) : ApiState<T>()
    class Failed(val message: String) : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
}