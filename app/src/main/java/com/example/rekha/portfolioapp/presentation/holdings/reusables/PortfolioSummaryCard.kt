package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rekha.R
import com.example.rekha.portfolioapp.presentation.holdings.screen.currencyFormat
import com.example.rekha.portfolioapp.presentation.holdings.state.PortfolioSummary
import com.example.rekha.ui.theme.PrimaryGreen
import com.example.rekha.ui.theme.PrimaryRed
import com.example.rekha.ui.theme.TextPrimary
import com.example.rekha.portfolioapp.presentation.holdings.utils.roundTo

@Composable
fun PortfolioSummaryCard(summary: PortfolioSummary, isExpanded: Boolean, onToggleExpand: () -> Unit) {
    val todaysPL = summary.todaysPnL
    val totalTodayPL =  todaysPL.roundTo(2)
    val pnlColor = if (summary.netPnL >= 0.0) PrimaryGreen else PrimaryRed
    val pnlColorForToday = if (totalTodayPL >= 0.0) PrimaryGreen else PrimaryRed
    Card(modifier  = Modifier
        .fillMaxWidth()
        .clip(MaterialTheme.shapes.large)
        .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceDim),
        elevation = CardDefaults.cardElevation(5.dp),
        ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (isExpanded) {
                SummaryItem(label = stringResource(R.string.current_value), summary.currentValue, Color.Black)
                Spacer(modifier = Modifier.height(16.dp))

                SummaryItem(label= stringResource(R.string.total_investment), summary.totalInvestment, Color.Black)
                Spacer(modifier = Modifier.height(16.dp))

                SummaryItem(stringResource(R.string.today_profit), totalTodayPL, pnlColorForToday)
                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            }
            // Net P&L Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggleExpand), // Clickable area for expand/collapse
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.profit_loss), fontWeight = FontWeight.SemiBold)
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    )
                }
                Text(
                    text = "${currencyFormat.format(summary.netPnL)} (${
                        currencyFormat.format(
                            summary.netPnLPercentage
                        )
                    }%)",
                    fontWeight = FontWeight.SemiBold,
                    color = pnlColor // Applying the P&L color logic
                )
            }
        }
    }
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .offset(y = (-5).dp) // Slightly push it down to hide the bottom shadow edge
    )

}
// Helper composable for summary items
@Composable
fun SummaryItem(label: String, value: Double, valueColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
        Text(
            text = currencyFormat.format(value),
            style = MaterialTheme.typography.bodyMedium,
            color = valueColor
        )
    }
}
