package com.devekkx.easy_bank.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier, onTxClick: (String) -> Unit,
    onTransferClick: () -> Unit,
    onLogout: () -> Unit
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

    }
}