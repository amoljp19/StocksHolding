package com.softaai.upstoxholding.data.repository

import androidx.annotation.MainThread
import com.softaai.upstoxholding.data.remote.Resource
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        val apiResponse = fetchFromRemote()

        val remoteHoldings = apiResponse.body()

        if (apiResponse.isSuccessful && remoteHoldings != null) {
            emitAll(
                emitFromRemote().map {
                    Resource.Success<RESULT>(it)
                }
            )
        } else {
            emit(Resource.Failed(apiResponse.message()))
        }

    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error! Can't get latest holdings."))
    }


    @MainThread
    protected abstract suspend fun emitFromRemote(): Flow<RESULT>


    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}