package com.example.rekha.portfolioapp.presentation.holdings

import com.example.rekha.portfolioapp.domain.model.Holding

data class HoldingsUiState(
    val isLoading: Boolean = false,
    val holdings: List<Holding> = emptyList(),
    val error: String? = null
) {
    val currentValue: Double
        get() = holdings.sumOf { it.ltp * it.qty }

    val totalInvestment: Double
        get() = holdings.sumOf { it.avgPrice * it.qty }

    val totalPNL: Double
        get() = currentValue - totalInvestment

    val todayPNL: Double
        get() = holdings.sumOf { (it.close - it.ltp) * it.qty }
}
