package com.example.rekha.portfolio.utilstest.usecase

import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.domain.repository.HoldingsRepository
import com.example.rekha.portfolioapp.domain.usecase.GetHoldingsUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetHoldingsUseCaseTest {

    private val repo: HoldingsRepository = mockk()
    private val useCase = GetHoldingsUseCase(repo)
    @Test
    fun `invoke emits success when repository returns list`() = runTest {
        val list = listOf(Holding("X", 1, 10.0, 9.0, 11.0))
        coEvery { repo.getHoldings() } returns list

        // FIX: collect the entire flow and read the final emission
        val emissions = useCase().toList()
        val result = emissions.last()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(list)
    }


    @Test
    fun `invoke emits failure when repository throws`() = runTest {
        coEvery { repo.getHoldings() } throws RuntimeException("network")

        val result = useCase().first()

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("network")
    }

}
