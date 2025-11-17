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

    val todaysPnL: Double get() = (ltp - close) * qty

}


