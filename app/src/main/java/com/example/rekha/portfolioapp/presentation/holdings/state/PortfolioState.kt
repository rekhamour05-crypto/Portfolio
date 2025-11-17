package com.example.rekha.portfolioapp.presentation.holdings.state

import com.example.rekha.portfolioapp.domain.model.Holding

data class PortfolioState(
    val holdings: List<Holding> = emptyList(),
    val summary: PortfolioSummary = PortfolioSummary(4.5, 784.6, 8.9),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)