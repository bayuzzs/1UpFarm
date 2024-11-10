package com.example.oneupfarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oneupfarm.ui.theme.Poppins

@Composable
fun genderScreen() {
    // Track which avatar is selected
    var selectedAvatarIndex by remember { mutableStateOf<Int?>(null) }

    // Determine background color based on the selected avatar
    val maleBackgroundColor = if (selectedAvatarIndex == 0) Color(0xFFE0F7FA) else Color(0xFFF5F5F5)
    val femaleBackgroundColor = if (selectedAvatarIndex == 1) Color(0xFFFFE4E1) else Color(0xFFF5F5F5)


    GradientBox(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_ellipse),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(414.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 145.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.63f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Avatar menyesuaikan gendermu,",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Pilih Gendermu!",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(38.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        // Male avatar selection
                        Box(
                            modifier = Modifier
                                .width(160.dp)
                                .height(224.dp)
                                .clip(RoundedCornerShape(22.dp))
                                .background(maleBackgroundColor)
                                .clickable { selectedAvatarIndex = 0 }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = if (selectedAvatarIndex == 0) R.drawable.ic_avatar_cowo else R.drawable.ic_avatar_cowo_bnw),
                                contentDescription = "Male Avatar",
                                modifier = Modifier.size(150.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(160.dp)
                                .height(224.dp)
                                .clip(RoundedCornerShape(22.dp))
                                .background(femaleBackgroundColor)
                                .clickable { selectedAvatarIndex = 1 }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = if (selectedAvatarIndex == 1) R.drawable.ic_avatar_cewe else R.drawable.ic_avatar_cewe_bnw),
                                contentDescription = "Female Avatar",
                                modifier = Modifier.size(150.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(62.dp))

                    Button(
                        onClick = { /* Handle action for selected avatar */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF661599)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = "Lanjut",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Kamu dapat merubahnya nanti di kustomisasi",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                    }

                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_mascot_bingung),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(263.dp)
                .align(Alignment.Center)
                .offset(y = -205.dp)
                .offset(x = -16.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun genderScreenPreview() {
    genderScreen()
}
