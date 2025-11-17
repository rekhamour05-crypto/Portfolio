package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rekha.portfolioapp.domain.model.Holding
import com.example.rekha.portfolioapp.presentation.holdings.screen.currencyFormat
import com.example.rekha.ui.theme.PrimaryGreen
import com.example.rekha.ui.theme.TextPrimary
import com.example.rekha.ui.theme.TextSecondary
import com.example.rekha.R
import com.example.rekha.ui.theme.PrimaryRed

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
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = stringResource(R.string.net_qnt),
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    text = holding.qty.toString(),
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            }
        }
        
        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.ltp), color = TextSecondary, fontSize = 12.sp)
                Text(" ${currencyFormat.format(holding.ltp)}", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Functionality: P&L Color Logic Applied
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.p_l), color = TextSecondary, fontSize = 12.sp)
                Text(
                    text = " ${currencyFormat.format(holding.pnl)}",
                    color = pnlColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}