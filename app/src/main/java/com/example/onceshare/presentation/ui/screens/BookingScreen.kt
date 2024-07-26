package com.example.onceshare.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onceshare.data.model.Booking
import com.example.onceshare.presentation.viewmodel.BookingViewModel

@Composable
fun BookingScreen(navController: NavController, applianceId: String) {
    val viewModel: BookingViewModel = hiltViewModel()
    val userId = "current_user_id_placeholder" // Replace with actual user ID
    val startDate = remember { mutableStateOf(0L) }
    val endDate = remember { mutableStateOf(0L) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Book Appliance")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = startDate.value.toString(),
            onValueChange = { startDate.value = it.toLongOrNull() ?: 0L },
            label = { Text("Start Date (timestamp)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = endDate.value.toString(),
            onValueChange = { endDate.value = it.toLongOrNull() ?: 0L },
            label = { Text("End Date (timestamp)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val booking = Booking(
                applianceId = applianceId,
                userId = userId,
                startDate = startDate.value,
                endDate = endDate.value
            )
            viewModel.addBooking(booking) { success: Boolean, message: String ->
                if (success) {
                    Toast.makeText(context, "Booking successful", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Book")
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddAppliancePrevie() {

}
