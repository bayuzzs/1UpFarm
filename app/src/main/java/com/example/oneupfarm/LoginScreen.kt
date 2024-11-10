@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.oneupfarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue


@Composable
fun loginScreen() {
    val isImeVisible by rememberImeState()

    GradientBox(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
                /* .fillMaxHeight(if (isImeVisible) 0f else 0.9f), */
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_ellipse),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(414.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 225.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ) {
                // Adding content inside the white background
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 46.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Welcome text
                    Text(
                        text = "Halo,",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Selamat Datang!",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(38.dp))

                    TextField(
                        value = "",
                        onValueChange = { /* Handle email input */ },
                        placeholder = { Text("E-mail Kamu") },
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color(0xFD9BAFF))
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            color = Color(0xF7C19B9)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFD9BAFF),
                            unfocusedIndicatorColor = Color.Transparent),
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    TextField(
                        value = "",
                        onValueChange = { /* Handle password input */ },
                        placeholder = { Text("Kata Sandi") },
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            color = Color(0xF7C19B9)
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFD9BAFF),
                            unfocusedIndicatorColor = Color.Transparent),
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = "",
                                tint = Color(0xF661599)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Lupa Password?",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF661599)
                        ),
                        modifier = Modifier.align(Alignment.End)
                            .padding(end = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(44.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF661599)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = "Masuk",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Belum punya akun?",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Daftar Sekarang",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF661599)
                            )
                        )
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_maskot_senyum),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(263.dp)
                    .align(Alignment.Center)
                    .offset(y = -142.dp)
            )
        }
    }
}



    @Preview(showBackground = true, widthDp = 412, heightDp = 917)
    @Composable
    fun loginScreenPreview() {
        loginScreen()
    }
