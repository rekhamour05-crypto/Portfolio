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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.presentation.holdings.screen.currencyFormat
import com.example.rekha.ui.theme.PrimaryGreen
import com.example.rekha.ui.theme.PrimaryRed
import com.example.rekha.ui.theme.TextPrimary
import com.example.rekha.ui.theme.TextSecondary

@Composable
fun HoldingsList(holdings: List<Holding>,
                 modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        // LazyColumn provides the essential, performant SCROLLING BEHAVIOR
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(holdings.size, key = { holdings[it].symbol }) { it ->
                HoldingRow(holdings[it])
                HorizontalDivider() // Divider after each item
            }
        }
    }
}

@Composable
fun HoldingRow(holding: Holding) {
    val pnlColor = if (holding.pnl >= 0) PrimaryGreen else PrimaryRed// Green or Red
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left Side: Name and Quantity
        Column(modifier = Modifier.weight(1f)) {
            Text(holding.symbol, fontWeight = FontWeight.SemiBold)
            Row {
                Text(
                    text = "NET QTY: ",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    text = holding.qty.toString(),
                    color = TextPrimary,
                    fontSize = 12.sp
                )
            }
        }
        
        // Right Side: LTP and P&L
        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("LTP: ", color = TextSecondary, fontSize = 12.sp)
                Text(" ${currencyFormat.format(holding.ltp)}", fontWeight = FontWeight.SemiBold)
            }
            
            // Functionality: P&L Color Logic Applied
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("P&L: ", color = TextSecondary, fontSize = 12.sp)
                Text(
                    text = " ${currencyFormat.format(holding.pnl)}",
                    color = pnlColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}