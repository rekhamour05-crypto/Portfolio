package com.example.rekha.portfolio.viewmodel

import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.domain.usecase.GetHoldingsUseCase
import com.example.rekha.portfolioapp.presentation.holdings.viewmodel.PortfolioViewModel
import com.example.rekha.portfolio.testutils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HoldingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetHoldingsUseCase = mockk()

    @Test
    fun `viewmodel loads holdings successfully and calculates summaries`() = runTest {
        val holdings = listOf(
            Holding("A", qty = 10, ltp = 100.0, avgPrice = 90.0, close = 105.0),
            Holding("B", qty = 5, ltp = 50.0, avgPrice = 40.0, close = 45.0)
        )

        every { useCase() } returns flow {
            emit(Result.success(holdings))
        }

        val vm = PortfolioViewModel(useCase)

        advanceUntilIdle()  // Wait for init{} to finish

        val state = vm.uiState.value

        assertThat(state.isLoading).isFalse()
        assertThat(state.holdings.size).isEqualTo(2)

        // currentValue = 10*100 + 5*50 = 1250
        assertThat(state.summary.currentValue).isEqualTo(1250.0)

        // totalInvestment = 10*90 + 5*40 = 1100
        assertThat(state.summary.totalInvestment).isEqualTo(1100.0)

        // todaysPnL = (105-100)*10 + (45-50)*5 = 50 - 25 = 25
        assertThat(state.summary.todaysPnL).isEqualTo(25.0)
    }
    @Test
    fun `viewmodel handles error`() = runTest {
        every { useCase() } returns flow {
            emit(Result.failure(RuntimeException("fail ")))
        }

        val vm = PortfolioViewModel(useCase)

        advanceUntilIdle()

        val state = vm.uiState.value

        assertThat(state.errorMessage).isEqualTo("fail ")
        assertThat(state.holdings).isEmpty()
        assertThat(state.isLoading).isFalse()
    }

}
