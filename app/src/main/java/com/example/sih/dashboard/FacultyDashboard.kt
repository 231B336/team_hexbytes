package com.example.sih.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun FacultyDashboard() {
    val db = FirebaseFirestore.getInstance()
    var submissions by remember { mutableStateOf(listOf<Map<String, Any>>()) }

    LaunchedEffect(true) {
        db.collection("activities").whereEqualTo("status", "pending")
            .addSnapshotListener { snapshot, _ ->
                submissions = snapshot?.documents?.map { it.data!! + mapOf("id" to it.id) } ?: listOf()
            }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome Faculty!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        submissions.forEach { submission ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Student: ${submission["studentName"]}")
                    Text("Activity: ${submission["activityName"]}")
                    Row {
                        Button(onClick = {
                            db.collection("activities").document(submission["id"].toString()).update("status", "approved")
                        }) { Text("Approve") }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = {
                            db.collection("activities").document(submission["id"].toString()).update("status", "rejected")
                        }) { Text("Reject") }
                    }
                }
            }
        }
    }
}
