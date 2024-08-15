package com.example.onceshare.presentation.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onceshare.R
import com.example.onceshare.data.model.Appliance
import com.example.onceshare.presentation.viewmodel.ApplianceViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

@Composable
fun AddApplianceScreen(navController: NavController) {
    val viewModel: ApplianceViewModel = hiltViewModel()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val condition = remember { mutableStateOf("") }
    val rentalPrice = remember { mutableStateOf("") }
    val availabilityPeriod = remember { mutableStateOf("") }
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> imageUri.value = uri }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageUri.value?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Selected Image",
                modifier = Modifier.size(200.dp)
            )
        } ?: Icon(
            painter = painterResource(R.drawable.ic_placeholder_image),
            contentDescription = "Placeholder Image",
            modifier = Modifier.size(200.dp),
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Pick Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            imageUri.value?.let { uri ->
                uploadImage(uri) { imageUrl ->
                    if (imageUrl != null) {
                        val appliance = Appliance(
                            name = name.value,
                            description = description.value,
                            condition = condition.value,
                            rentalPrice = rentalPrice.value.toDoubleOrNull() ?: 0.0,
                            ownerId = "ownerId_placeholder", // Replace with actual owner ID
                            availabilityPeriod = availabilityPeriod.value,
                            imageUri = imageUrl
                        )
                        viewModel.addAppliance(appliance) { success, message ->
                            if (success) {
                                Toast.makeText(context, "Appliance added successfully", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }) {
            Text("Add Appliance")
        }
    }
}


fun uploadImage(uri: Uri, onComplete: (String?) -> Unit) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")

    val uploadTask = imageRef.putFile(uri)
    uploadTask.addOnSuccessListener {
        imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
            onComplete(downloadUrl.toString())
        }.addOnFailureListener {
            onComplete(null)
        }
    }.addOnFailureListener {
        onComplete(null)
    }
}
