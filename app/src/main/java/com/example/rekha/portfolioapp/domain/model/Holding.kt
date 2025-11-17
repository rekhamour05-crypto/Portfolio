package com.example.rekha.portfolioapp.domain.model

data class Holding(
    val symbol: String,
    val qty: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
) {
    val currentHoldingValue = ltp * qty
    val totalInvestment = avgPrice * qty
    val pnl: Double = currentHoldingValue - totalInvestment

    val pnlPercent: Double
        get() = (pnl / (avgPrice * qty)) * 100
    val todaysPnL: Double get() = (close - ltp) * qty   // ✔ NECESSARY


}


