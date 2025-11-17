package com.example.rekha.portfolioapp.domain.usecase

import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.domain.repository.HoldingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHoldingsUseCase @Inject constructor(
    private val repository: HoldingsRepository
) {
    operator fun invoke(): Flow<Result<List<Holding>>> = flow {
        try {
            emit(Result.success(repository.getHoldings()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
