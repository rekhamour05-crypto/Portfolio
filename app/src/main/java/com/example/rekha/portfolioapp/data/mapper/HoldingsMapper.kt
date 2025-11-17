package com.example.rekha.portfolioapp.data.mapper

import com.example.rekha.portfolioapp.data.model.HoldingDto
import com.example.rekha.portfolioapp.data.model.HoldingResponse
import com.example.rekha.portfolioapp.domain.model.Holding

object HoldingsMapper {

    fun mapResponseToDomain(response: HoldingResponse): List<Holding> {
        val list = response.data?.userHolding ?: emptyList()
        return list.map { mapDtoToDomain(it) }
    }

    private fun mapDtoToDomain(dto: HoldingDto): Holding {
        return Holding(
            symbol = dto.symbol ?: "N/A",
            qty = dto.quantity ?: 0,
            ltp = dto.ltp ?: 0.0,
            avgPrice = dto.avgPrice ?: 0.0,
            close = dto.close ?: 0.0
        )
    }
}