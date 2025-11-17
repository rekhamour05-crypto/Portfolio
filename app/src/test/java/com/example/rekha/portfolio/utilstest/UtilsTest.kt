package com.example.rekha.portfolio.utilstest

import com.example.rekha.portfolioapp.presentation.holdings.screen.roundTo
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {

    @Test
    fun `roundTo extension works`() {
        val v = 12.34567
        val rounded = v.roundTo(2)
        assertThat(rounded).isEqualTo(12.35)
    }
}
