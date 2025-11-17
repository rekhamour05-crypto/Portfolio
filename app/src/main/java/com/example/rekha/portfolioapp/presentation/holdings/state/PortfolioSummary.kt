package com.example.rekha.portfolioapp.presentation.holdings.state

data class PortfolioSummary(
    val currentValue: Double =0.0,
    val totalInvestment: Double =0.0,
    val todaysPnL: Double =0.0 // Today's Profit & Loss
) {
    val netPnL = currentValue - totalInvestment
    val netPnLPercentage = (netPnL / totalInvestment) * 100
}