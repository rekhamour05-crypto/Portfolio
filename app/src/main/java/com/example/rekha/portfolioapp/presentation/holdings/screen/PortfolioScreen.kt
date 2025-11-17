package com.example.rekha.portfolioapp.presentation.holdings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.presentation.holdings.HoldingsUiState
import com.example.rekha.portfolioapp.presentation.holdings.viewmodel.PortfolioViewModel
import com.example.rekha.portfolioapp.presentation.holdings.reusables.AppToolbar
import com.example.rekha.portfolioapp.presentation.holdings.reusables.HoldingsList
import com.example.rekha.portfolioapp.presentation.holdings.reusables.PortfolioSummaryCard
import com.example.rekha.portfolioapp.presentation.holdings.reusables.RetryButton
import com.example.rekha.portfolioapp.presentation.holdings.reusables.ShimmerList
import com.example.rekha.ui.theme.HoldingsTheme
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.round
import com.example.rekha.R


val currencyFormat = DecimalFormat("₹#,##0.00")

@Composable
fun PortfolioScreen() {
    val viewModel: PortfolioViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    var isSummaryExpanded by remember { mutableStateOf(false) } // Initially collapsed

    Scaffold(
        topBar = { AppToolbar() },
        bottomBar = { }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        ShimmerList()
                    }
                }

                state.errorMessage  != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(
                                text = state.errorMessage ?: stringResource(R.string.something_went_wrong),
                                color = MaterialTheme.colorScheme.error,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            RetryButton(onRetry = { viewModel.loadHoldings() })
                        }
                    }
                }

                else -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        HoldingsList(
                            state.holdings,
                            modifier = Modifier.weight(1f)
                        )
                        PortfolioSummaryCard(
                            state.summary,
                            isExpanded = isSummaryExpanded,
                            onToggleExpand = { isSummaryExpanded = !isSummaryExpanded })
                    }
                }
            }
        }
    }

}

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return round((this * factor)) / factor
}


@Preview(showBackground = true)
@Composable
fun HoldingsScreenPreview() {
    val fakeHoldings = listOf(
        Holding(symbol = "AAPL", ltp = 173.2, avgPrice = 150.0, qty = 10, close = 174.5),
        Holding(symbol = "GOOG", ltp = 134.1, avgPrice = 120.0, qty = 5, close = 135.0),
        Holding(symbol = "TSLA", ltp = 240.8, avgPrice = 230.0, qty = 4, close = 243.0)
    )

    val previewState = HoldingsUiState(
        holdings = fakeHoldings,
        isLoading = false
    )

    HoldingsTheme {
      PortfolioScreen()
    }
}