package com.example.sih.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StudentDashboard(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome Student!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("upload_activity") }) { Text("Upload Activity") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { navController.navigate("portfolio") }) { Text("Generate Portfolio") }
    }
}
