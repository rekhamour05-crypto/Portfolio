package com.example.rekha.portfolioapp.presentation.holdings.reusables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rekha.ui.theme.Primary
import com.example.rekha.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar( onSortClick: () -> Unit = {},
                onSearchClick: () -> Unit = {},
                onProfileClick: () -> Unit = {}) {

    TopAppBar(
        title =  {
            Text(
                text = "Portfolio",
                fontWeight = FontWeight.Bold,
                color = white
            )
        },
        navigationIcon = {
            // Profile avatar (left side)
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = white,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        actions = {
            // Sort up/down arrow toggle
            IconButton(onClick = onSortClick) {
                Icon(
                    imageVector = Icons.Filled.SwapVert,
                    contentDescription = "Sort",
                    tint = white,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Search icon (right side)
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = white
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,  // your navy blue theme color
            titleContentColor = white,
            actionIconContentColor = white,
            navigationIconContentColor = white
        ),
        modifier = Modifier.fillMaxWidth(),
    )
    // Horizontal rule to separate the toolbar from the content below
    HorizontalDivider(thickness = 0.5.dp) 
}