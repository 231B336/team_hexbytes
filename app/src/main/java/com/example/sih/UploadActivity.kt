package com.example.sih

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun UploadActivityScreen(navController: NavController) {
    var activityName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var credits by remember { mutableStateOf("") }
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val userId = auth.currentUser!!.uid

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Upload Activity", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        TextField(value = activityName, onValueChange = { activityName = it }, label = { Text("Activity Name") })
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        TextField(value = credits, onValueChange = { credits = it }, label = { Text("Credits") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val activity = hashMapOf(
                "studentId" to userId,
                "studentName" to auth.currentUser!!.displayName,
                "activityName" to activityName,
                "category" to category,
                "credits" to credits.toIntOrNull(),
                "status" to "pending",
                "timestamp" to FieldValue.serverTimestamp()
            )
            db.collection("activities").add(activity).addOnSuccessListener {
                Toast.makeText(context, "Activity uploaded!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }) { Text("Submit") }
    }
}
