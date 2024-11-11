package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.theme.Poppins
import com.example.oneupfarm.ui.theme.purple900
import com.example.oneupfarm.ui.theme.white




@Composable
fun WelcomeScreen(
    navController: NavController= rememberNavController(),
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),

        ) {

        Image(
            painter = painterResource(id = R.drawable.ic_background_login),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(62.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_logo_login),
                contentDescription = null,
                modifier = Modifier
                    .width(218.dp)
                    .height(241.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Tanam berbagai tanaman,\n selesaikan misi harian, dan dapatkan poin untuk naik level!",
                style = TextStyle(
                fontSize = 18.sp,
                fontFamily = Poppins,
                color = Color.White,
                fontWeight = Medium,
                textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(38.dp))

            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = white),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 4.dp)
                    .height(56.dp)
            ) {
                Text(
                    text = "Masuk",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = purple900
                    )

                    )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate(Screen.Register.route) },
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(2.dp, Color.White),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple900),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 4.dp)
                    .height(56.dp)
            ){
                Text(text = "Daftar",
                    style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
