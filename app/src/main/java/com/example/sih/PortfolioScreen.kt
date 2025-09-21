package com.example.sih



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sih.PDFGenerator

@Composable
fun PortfolioScreen() {
    val context = LocalContext.current

    // Example list of student activities
    val activities = listOf(
        mapOf("activityName" to "Math Seminar", "category" to "Seminar", "credits" to 2),
        mapOf("activityName" to "AI Workshop", "category" to "Workshop", "credits" to 3),
        mapOf("activityName" to "Football Tournament", "category" to "Sports", "credits" to 1)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Student Portfolio",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Button to generate PDF
        Button(onClick = {
            PDFGenerator.generateStudentPortfolio(context, activities)
        }) {
            Text("Generate PDF")
        }
    }
}
