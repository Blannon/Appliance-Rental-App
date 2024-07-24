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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onceshare.presentation.viewmodel.UserViewModel

@Composable
fun UserProfileScreen(navController: NavController) {
    val viewModel: UserViewModel = hiltViewModel()
    val userId = "current_user_id_placeholder" // Replace with actual user ID
    val user by viewModel.getUserById(userId).observeAsState()
    val context = LocalContext.current

    user?.let { nonNullUser ->
        val name = remember { mutableStateOf(nonNullUser.name) }
        val email = remember { mutableStateOf(nonNullUser.email) }
        val profilePictureUrl = remember { mutableStateOf(nonNullUser.profilePictureUrl) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = profilePictureUrl.value,
                onValueChange = { profilePictureUrl.value = it },
                label = { Text("Profile Picture URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val updatedUser = nonNullUser.copy(
                    name = name.value,
                    email = email.value,
                    profilePictureUrl = profilePictureUrl.value
                )
                viewModel.updateUser(updatedUser) { success: Boolean, message: String ->
                    if (success) {
                        Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Update Profile")
            }
        }
    }
}
