package com.softaai.upstoxholding.repository

import com.softaai.upstoxholding.model.HoldingsApiResponse
import com.softaai.upstoxholding.remote.HoldingsApiService
import com.softaai.upstoxholding.remote.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface HoldingsRepository {
    fun getAllHoldings(): Flow<Resource<HoldingsApiResponse>>
   }

class DefaultHoldingsRepository @Inject constructor(
    private val holdingsApiService: HoldingsApiService
) : HoldingsRepository {

    override fun getAllHoldings(): Flow<Resource<HoldingsApiResponse>> {
       return flowOf()
    }
}