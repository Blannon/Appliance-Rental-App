package com.example.onceshare.presentation.ui.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onceshare.data.model.Appliance
import com.example.onceshare.presentation.viewmodel.ApplianceViewModel

@Composable
fun AddApplianceScreen(navController: NavController) {
    val viewModel: ApplianceViewModel = hiltViewModel()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val condition = remember { mutableStateOf("") }
    val rentalPrice = remember { mutableStateOf("") }
    val availabilityPeriod = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = condition.value,
            onValueChange = { condition.value = it },
            label = { Text("Condition") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = rentalPrice.value,
            onValueChange = { rentalPrice.value = it },
            label = { Text("Rental Price") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = availabilityPeriod.value,
            onValueChange = { availabilityPeriod.value = it },
            label = { Text("Availability Period") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val appliance = Appliance(
                name = name.value,
                description = description.value,
                condition = condition.value,
                rentalPrice = rentalPrice.value.toDoubleOrNull() ?: 0.0,
                ownerId = "ownerId_placeholder", // Replace with actual owner ID
                availabilityPeriod = availabilityPeriod.value
            )
            viewModel.addAppliance(appliance) { success, message ->
                if (success) {
                    Toast.makeText(context, "Appliance added successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Add Appliance")
        }
    }
}
