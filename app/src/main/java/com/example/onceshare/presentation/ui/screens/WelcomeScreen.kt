package com.example.onceshare.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.onceshare.CButton
import com.example.onceshare.DontHaveAccountRow
import com.example.onceshare.R
import com.example.onceshare.presentation.ui.theme.AlegreyaFontFamily
import com.example.onceshare.presentation.ui.theme.AlegreyaSansFontFamily


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFF253334),
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            /// Content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    modifier = Modifier
                        .width(320.dp)
                        .height(240.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    "WELCOME",
                    fontSize = 32.sp,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight(700),
                    color = Color.White
                )

                Text(
                    "",
                    textAlign = TextAlign.Center,
                    fontFamily = AlegreyaSansFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))


                CButton(text = "Sign In With Email",
                    onClick = {
                        navController.navigate("login")
                    }
                )

                DontHaveAccountRow(
                    onSignupTap = {
                        navController.navigate("signup")
                    }
                )



            }
        }
    }



}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}