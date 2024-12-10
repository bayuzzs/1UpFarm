package com.example.oneupfarm.ui.screen

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.ui.component.OUFBackground
import com.example.oneupfarm.R
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.theme.Poppins
import com.example.oneupfarm.utils.showSnackbar
import com.example.oneupfarm.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChooseGenderScreen(navController: NavController, authViewModel: AuthViewModel) {
    // Track which avatar is selected
    var selectedAvatar = remember { mutableStateOf<Gender>(Gender.M) }

    // Determine background color based on the selected avatar
    val maleBackgroundColor =
        if (selectedAvatar.value == Gender.M) Color(0xFFE0F7FA) else Color(0xFFF5F5F5)
    val femaleBackgroundColor =
        if (selectedAvatar.value == Gender.F) Color(0xFFFFE4E1) else Color(0xFFF5F5F5)

//    val scope = rememberCoroutineScope()
    val isLoading = authViewModel.isLoading.collectAsState()
//    val message = authViewModel.message.collectAsState()
    val snackbarHost = remember { SnackbarHostState() }

    val user = authViewModel.user.collectAsState()

//    if (message.value != null) {
//        LaunchedEffect(message.value) {
//            showSnackbar(snackbarHost, scope, message.value)
//            authViewModel.clearMessage()
//        }
//    }

    val navigationEvent = authViewModel.navigationEvent.collectAsState()
    LaunchedEffect(navigationEvent.value) {
        navigationEvent.value?.let { screen ->
            navController.navigate(screen.route)
            authViewModel.resetNavigate()
        }
    }

    Scaffold(
//        snackbarHost = {
//        SnackbarHost(hostState = snackbarHost) {
//            Snackbar(
//                snackbarData = it,
//                containerColor = Color(0xFF7C19B9),
//                contentColor = Color.White
//            )
//
//        }
//    }
    ) {
        OUFBackground(modifier = Modifier.fillMaxSize()) {
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
                            .padding(start = 30.dp, end = 30.dp, top = 54.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Avatar menyesuaikan gendermu,",
                            style = TextStyle(
                                fontSize = 14.sp,
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

                        Spacer(modifier = Modifier.height(25.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            // Male avatar selection
                            Box(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(maleBackgroundColor)
                                    .clickable { selectedAvatar.value = Gender.M }
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = if (selectedAvatar.value == Gender.M) R.drawable.boyavatar else R.drawable.boybnw),
                                    contentDescription = "Male Avatar",
                                    modifier = Modifier.size(150.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(femaleBackgroundColor)
                                    .clickable { selectedAvatar.value = Gender.F }
                                    .padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = if (selectedAvatar.value == Gender.F) R.drawable.girlavatar else R.drawable.girlbnw),
                                    contentDescription = "Female Avatar",
                                    modifier = Modifier.size(140.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(35.dp))

                        Button(
                            onClick = {
                                if (user.value?.name.isNullOrBlank() || user.value?.email.isNullOrBlank() || user.value?.password.isNullOrBlank()) {
                                    authViewModel.setMessage("Semua field harus diisi!")
                                    return@Button
                                }

                                authViewModel.register(
                                    user.value?.name ?: "",
                                    user.value?.email ?: "",
                                    user.value?.password ?: "",
                                    selectedAvatar.value
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF661599)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(57.dp),
                            shape = RoundedCornerShape(50.dp),
                            enabled = !isLoading.value
                        ) {
                            if (isLoading.value) {
                                CircularProgressIndicator()
                            } else {
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
                painter = painterResource(id = R.drawable.confusedmascot),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(243.dp)
                    .align(Alignment.Center)
                    .offset(y = (-195).dp)
                    .offset(x = (-16).dp)
            )
        }
    }
}

//@Preview(showBackground = true, widthDp = 412, heightDp = 917)
//@Composable
//fun GenderScreenPreview() {
//    ChooseGenderScreen(navController = rememberNavController())
//}
