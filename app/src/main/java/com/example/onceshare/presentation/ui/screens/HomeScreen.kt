package com.example.onceshare.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onceshare.presentation.ui.theme.BlueEnd
import com.example.onceshare.presentation.ui.theme.BlueStart
import com.example.onceshare.utils.getGradient

@Composable
fun HomeScreen(navController: NavController) {


    val items = listOf(
        Triple("View Appliances", "appliance_list", Icons.Default.Home),
        Triple("Add Appliance", "add_appliance", Icons.Default.Add),
        Triple("Book", "booking", Icons.Default.Book),
        Triple("User", "user", Icons.Default.Person)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { (text, route, icon) ->
            ClickableCardItem(
                text = text,
                icon = icon,
                color = getGradient(BlueStart, BlueEnd),
                onClick = { navController.navigate(route) }
            )
        }
    }
}

@Composable
fun ClickableCardItem(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Brush,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(color)
                .width(250.dp)
                .height(160.dp)
                .clickable { onClick() }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))


            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}
