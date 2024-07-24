//package com.example.onceshare.presentation.ui.screens
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.Card
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.onceshare.presentation.viewmodel.ApplianceViewModel
//
//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun ApplianceListScreen(navController: NavController) {
//    val viewModel: ApplianceViewModel = hiltViewModel()
//    val appliances by viewModel.appliances.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.getAppliances()
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp)
//    ) {
//        TextField(
//            value = viewModel.searchQuery.value,
//            onValueChange = { viewModel.searchQuery.value = it },
//            label = { Text("Search Appliances") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        LazyColumn {
//            items(appliances) { appliance ->
//                ApplianceItem(appliance)
//            }
//        }
//        FloatingActionButton(
//            onClick = { navController.navigate("add_appliance") },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Icon(Icons.Default.Add, contentDescription = "Add Appliance")
//        }
//
//    }
//}
//
//@Composable
//fun ApplianceItem(appliance: Int) {
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
//        //elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(appliance.name, style = MaterialTheme.typography.headlineMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(appliance.description)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Condition: ${appliance.condition}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Rental Price: $${appliance.rentalPrice}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Availability Period: ${appliance.availabilityPeriod}")
//        }
//    }
//}
