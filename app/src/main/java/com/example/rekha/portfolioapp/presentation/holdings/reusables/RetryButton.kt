package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rekha.R

@Composable
fun RetryButton(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )
            .clickable { onRetry() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.retry),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
