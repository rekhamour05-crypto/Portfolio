package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
) {
    val transition = rememberInfiniteTransition()

    val translateAnim = transition.animateFloat(
        initialValue = -200f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.4f),
            Color.LightGray.copy(alpha = 0.1f),
            Color.LightGray.copy(alpha = 0.4f),
        ),
        start = Offset(translateAnim.value, translateAnim.value),
        end = Offset(translateAnim.value + 200f, translateAnim.value + 200f)
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(shimmerBrush)
    )
}

@Composable
fun ShimmerList(count: Int = 6) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        repeat(count) {
            ShimmerItem()
        }
    }
}
