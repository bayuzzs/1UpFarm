@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.ui.component.OUFBackground
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.navigation.Screen


@Composable
fun RegisterScreen(navController: NavController= rememberNavController()) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OUFBackground(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_ellipse),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(414.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 95.dp)
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
                    // Welcome text
                    Text(
                        text = "Halo,",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Text(
                        text = "Daftar Sekarang!",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = {
                            Text(
                                text = "Nama Lengkap",
                                style = TextStyle(
                                    fontFamily = Poppins,
                                    fontSize = 18.sp,
                                    color = Color(0xFF7C19B9)
                                )
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            color = Color(0xFF7C19B9)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFD9BAFF),
                            focusedContainerColor = Color(0xFFD9BAFF)
                            ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions.Default
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = {
                            Text(
                                text = "E-mail Kamu",
                                style = TextStyle(
                                    fontFamily = Poppins,
                                    fontSize = 18.sp,
                                    color = Color(0xFF7C19B9)
                                )
                            )
                        },
                        shape = RoundedCornerShape(11.dp),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            color = Color(0xFF7C19B9)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFD9BAFF),
                            focusedContainerColor = Color(0xFFD9BAFF)
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions.Default
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                text = "Kata Sandi",
                                style = TextStyle(
                                    fontFamily = Poppins,
                                    fontSize = 18.sp,
                                    color = Color(0xFF7C19B9)
                                )
                            )
                        },
                        shape = RoundedCornerShape(11.dp),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        textStyle = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            color = Color(0xFF7C19B9)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFD9BAFF),
                            focusedContainerColor = Color(0xFFD9BAFF)
                        ),
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color(0xFF661599),
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(35.dp))

                    Button(
                        onClick = { navController.navigate(Screen.ChooseGender.route) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF661599)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp),
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = "Daftar",
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
                            text = "Sudah punya akun?",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Masuk",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF661599)
                            ),
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Login.route)
                            }
                        )
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.nicemascot),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(243.dp)
                    .align(Alignment.Center)
                    .offset(y = (-190).dp)
                    .offset(x = 16.dp)
            )
        }
    }
}



@Preview(showBackground = true, widthDp = 412, heightDp = 917)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
