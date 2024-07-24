//package com.example.onceshare.presentation.ui.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.onceshare.data.model.Booking
//import com.example.onceshare.presentation.viewmodel.ApplianceViewModel
//import kotlinx.coroutines.flow.collectAsState
//
//@Composable
//fun ApplianceDetailScreen(navController: NavController, applianceId: String) {
//    val viewModel: ApplianceViewModel = hiltViewModel()
//    val appliance by viewModel.getApplianceById(applianceId).collectAsState(initial = null)
//    val bookings by viewModel.getBookingsByAppliance(applianceId).collectAsState(initial = emptyList())
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        appliance?.let { appliance ->
//            Text(appliance.name, style = MaterialTheme.typography.headlineMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(appliance.description)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Condition: ${appliance.condition}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Rental Price: $${appliance.rentalPrice}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Availability Period: ${appliance.availabilityPeriod}")
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = { navController.navigate("booking/$applianceId") }) {
//                Text("Book Appliance")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Bookings:")
//            LazyColumn {
//                items(bookings) { booking ->
//                    BookingItem(booking)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BookingItem(booking: Booking) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text("Booking ID: ${booking.id}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("User ID: ${booking.userId}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Start Date: ${booking.startDate}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("End Date: ${booking.endDate}")
//            Spacer(modifier = Modifier.height(8.dp))
//            Text("Status: ${booking.status}")
//        }
//    }
//}
