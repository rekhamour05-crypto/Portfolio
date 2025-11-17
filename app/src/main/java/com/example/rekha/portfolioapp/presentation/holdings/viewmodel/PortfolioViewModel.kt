package com.example.rekha.portfolioapp.presentation.holdings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.domain.usecase.GetHoldingsUseCase
import com.example.rekha.portfolioapp.presentation.holdings.state.PortfolioState
import com.example.rekha.portfolioapp.presentation.holdings.state.PortfolioSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getHoldingsUseCase: GetHoldingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioState())
    val uiState: StateFlow<PortfolioState> = _uiState.asStateFlow()

    init {
        loadHoldings()
    }

    fun loadHoldings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            getHoldingsUseCase().collect { result ->
                result.onSuccess { list ->
                  _uiState.value=  _uiState.value.copy(
                        holdings = list.sortedByDescending { it.pnl },
                        summary = calculateSummary(list),
                        isLoading = false
                    )

                    val summary = calculateSummary(list)
                    _uiState.value = _uiState.value.copy(
                        holdings = list.sortedByDescending { it.pnl },
                        summary = summary,
                        isLoading = false
                    )
                }
                result.onFailure { e ->
                    _uiState.value=  _uiState.value.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    private fun calculateSummary(holdings: List<Holding>): PortfolioSummary {
        val totalInvestment = holdings.sumOf { it.totalInvestment }
        val currentValue = holdings.sumOf { it.currentHoldingValue }
        val netPnL = currentValue - totalInvestment

        val todaysPnL = holdings.sumOf { it.todaysPnL }

        return PortfolioSummary(
            currentValue = roundToTwoDecimals(currentValue),
            totalInvestment = roundToTwoDecimals(totalInvestment),
            todaysPnL = roundToTwoDecimals(todaysPnL),
        )
    }
    private fun roundToTwoDecimals(value: Double): Double {
        return (value * 100).roundToInt() / 100.0
    }
}