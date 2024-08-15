package com.example.onceshare.presentation.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.onceshare.R
import com.example.onceshare.data.model.Appliance
import com.example.onceshare.presentation.ui.theme.BlueEnd
import com.example.onceshare.presentation.ui.theme.BlueStart
import com.example.onceshare.presentation.viewmodel.ApplianceViewModel
import com.example.onceshare.utils.getGradient

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ApplianceListScreen(navController: NavController) {
    val viewModel: ApplianceViewModel = hiltViewModel()
    val appliances by viewModel.appliances.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAppliances()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = viewModel.searchQuery.value,
            onValueChange = { viewModel.searchQuery.value = it },
            label = { Text("Search Appliances") },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = getGradient(BlueStart, BlueEnd),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(appliances) { appliance ->
                ApplianceItem(appliance = appliance, viewModel = viewModel, navController)
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("add_appliance") },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Appliance")
        }
    }
}
@Composable
fun ApplianceItem(appliance: Appliance, viewModel: ApplianceViewModel, navController: NavController) {
    val imageUrl by viewModel.getImageUrl(appliance.imageUri ?: "").collectAsState("")
    Log.d("ApplianceItem", "Image URL: $imageUrl")

    val cardColor = getGradient(BlueStart, BlueEnd)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("booking") },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(cardColor)
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    imageUrl.takeIf { it.isNotBlank() }?.let {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = it,
                                placeholder = painterResource(id = R.drawable.baseline_image_24)
                            ),
                            contentDescription = "Appliance Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .aspectRatio(1f),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            appliance.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            appliance.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Condition: ${appliance.condition}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Rental Price: Ksh${appliance.rentalPrice}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Availability Period: ${appliance.availabilityPeriod}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


