package com.example.rekha.portfolioapp.data.remote

import com.example.rekha.portfolioapp.data.model.HoldingResponse
import retrofit2.http.GET

interface HoldingsApi {
    @GET("/")
    suspend fun getHoldings(): HoldingResponse
}
