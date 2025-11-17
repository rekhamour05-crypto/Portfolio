package com.example.rekha.portfolioapp.domain.repository

import com.example.rekha.portfolioapp.domain.model.Holding
import kotlinx.coroutines.flow.Flow

interface HoldingsRepository {
    suspend fun getHoldings(): List<Holding>
}
