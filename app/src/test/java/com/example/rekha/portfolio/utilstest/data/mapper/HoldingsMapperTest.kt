package com.example.rekha.portfolio.data.mapper

import com.example.rekha.portfolioapp.data.mapper.HoldingsMapper
import com.example.rekha.portfolioapp.data.model.HoldingData
import com.example.rekha.portfolioapp.data.model.HoldingDto
import com.example.rekha.portfolioapp.data.model.HoldingResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HoldingsMapperTest {

    @Test
    fun `mapResponseToDomain maps non-null values correctly`() {
        val dto = HoldingDto(
            symbol = "TCS",
            quantity = 10,
            ltp = 3500.0,
            avgPrice = 3400.0,
            close = 3450.0
        )
        val response = HoldingResponse(HoldingData(listOf(dto)))

        val domain = HoldingsMapper.mapResponseToDomain(response)

        assertThat(domain).hasSize(1)
        val item = domain[0]
        assertThat(item.symbol).isEqualTo("TCS")
        assertThat(item.qty).isEqualTo(10)
        assertThat(item.ltp).isEqualTo(3500.0)
        assertThat(item.avgPrice).isEqualTo(3400.0)
        assertThat(item.close).isEqualTo(3450.0)
    }

    @Test
    fun `mapResponseToDomain handles null data gracefully`() {
        val response = HoldingResponse(data = null)
        val domain = HoldingsMapper.mapResponseToDomain(response)
        assertThat(domain).isEmpty()
    }

    @Test
    fun `mapResponseToDomain maps null fields to defaults`() {
        val dto = HoldingDto(symbol = null, quantity = null, ltp = null, avgPrice = null, close = null)
        val response = HoldingResponse(HoldingData(listOf(dto)))

        val domain = HoldingsMapper.mapResponseToDomain(response)
        assertThat(domain).hasSize(1)
        val item = domain[0]
        assertThat(item.symbol).isEqualTo("N/A")
        assertThat(item.qty).isEqualTo(0)
        assertThat(item.ltp).isEqualTo(0.0)
        assertThat(item.avgPrice).isEqualTo(0.0)
        assertThat(item.close).isEqualTo(0.0)
    }
}
