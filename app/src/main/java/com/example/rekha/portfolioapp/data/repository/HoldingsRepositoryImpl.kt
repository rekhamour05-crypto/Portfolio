package com.example.rekha.portfolioapp.data.repository

import com.example.rekha.portfolioapp.data.mapper.HoldingsMapper
import com.example.rekha.portfolioapp.data.remote.HoldingsApi
import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.domain.repository.HoldingsRepository
import javax.inject.Inject

class HoldingsRepositoryImpl @Inject constructor(
    private val api: HoldingsApi
) : HoldingsRepository {
    override suspend fun getHoldings(): List<Holding> {
        val response = api.getHoldings()
        return HoldingsMapper.mapResponseToDomain(response)
    }
}
