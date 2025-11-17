package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.rekha.portfolioapp.domain.model.Holding


@Composable
fun HoldingsList(holdings: List<Holding>,
                 modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(holdings.size, key = { holdings[it].symbol }) { it ->
                HoldingRow(holdings[it])
                HorizontalDivider()
            }
        }
    }
}