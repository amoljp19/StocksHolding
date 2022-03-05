package com.softaai.upstoxholding.data.remote

import com.softaai.upstoxholding.data.model.HoldingsApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface HoldingsApiService {

    @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getHoldings(): Response<HoldingsApiResponse>

    companion object {
        const val API_URL = "https://run.mocky.io"
    }

}