package com.softaai.upstoxholding.data.repository

import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import com.softaai.upstoxholding.data.remote.HoldingsApiService
import com.softaai.upstoxholding.data.remote.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import javax.inject.Inject

interface HoldingsRepository {
    fun getAllHoldings(): Flow<Resource<HoldingsApiResponse>>
}

class DefaultHoldingsRepository @Inject constructor(
    private val holdingsApiService: HoldingsApiService
) : HoldingsRepository {

    override fun getAllHoldings(): Flow<Resource<HoldingsApiResponse>> {
        return object :
            NetworkBoundRepository<HoldingsApiResponse, HoldingsApiResponse>() {

            override suspend fun emitFromRemote(): Flow<HoldingsApiResponse> =
                flowOf(fetchFromRemote().body()!!)

            override suspend fun fetchFromRemote(): Response<HoldingsApiResponse> =
                holdingsApiService.getHoldings()

        }.asFlow()
    }
}