package com.ziss.core.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiState<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun toFlow() = flow {
        Log.d("Popular", "loaded")
        emit(ResultState.Loading())

        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            emit(ResultState.Loading())

            val apiResponse = createCall().first()
            when (apiResponse) {
                is ApiState.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { ResultState.Success(it) })
                }

                is ApiState.Empty -> {
                    emitAll(loadFromDB().map { ResultState.Success(it) })
                }

                is ApiState.Failed -> {
                    onFetchFailed()
                    emit(ResultState.Failed(apiResponse.message))
                }
            }
        } else {
            emitAll(loadFromDB().map { ResultState.Success(it) })
        }
    }
}