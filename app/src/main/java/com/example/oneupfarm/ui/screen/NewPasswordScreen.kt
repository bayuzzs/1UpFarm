@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.GradientBox
import com.example.oneupfarm.R
import com.example.oneupfarm.rememberImeState


@Composable
fun NewPasswordScreen(navController: NavController= rememberNavController()) {
    val isImeVisible by rememberImeState()

    GradientBox(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            /* .fillMaxHeight(if (isImeVisible) 0f else 0.9f), */
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 74.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Handle back action */ },
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(onClick = { /* Handle close action */ },
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

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
                        .padding(start = 30.dp, end = 30.dp, top = 46.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                        text = "Daftar Kembali!",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Masukkan kata sandi baru dibawah ini.",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
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
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFFD9BAFF),
                            unfocusedIndicatorColor = Color.Transparent),
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    TextField(
                        value = "",
                        onValueChange = { /* Handle email input */ },
                        placeholder = { Text("Kata Sandi Baru") },
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
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFFD9BAFF),
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

                    Spacer(modifier = Modifier.height(18.dp))

                    TextField(
                        value = "",
                        onValueChange = { /* Handle password input */ },
                        placeholder = { Text("Konfirmasi Kata Sandi") },
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
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFFD9BAFF),
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

                    Spacer(modifier = Modifier.height(48.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF661599)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = "Kirim",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }


                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_maskot_jempol),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(263.dp)
                    .align(Alignment.Center)
                    .offset(y = -205.dp)
                    .offset(x = 16.dp)
            )
        }
    }
}



@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun newPasswordScreenPreview() {
    NewPasswordScreen()
}
