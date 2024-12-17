package com.example.oneupfarm.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.SubcomposeAsyncImage
import com.example.oneupfarm.data.api.RetrofitClient
import com.example.oneupfarm.data.model.Plant
import com.example.oneupfarm.data.model.UserPlant
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.viewmodel.PlantViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun AddPlantConfirmation(
    showConfirmation: MutableState<Boolean>,
    plant: Plant,
    methodPlant: String,
    locationPlant: String,
    plantViewModel: PlantViewModel,
    onNavigateToNextScreen: () -> Unit,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val isLoading = plantViewModel.isLoading.collectAsState()
    val showSuccess = remember { mutableStateOf(false) }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val today = LocalDate.now()
    val harvestDay = plant.harvestDay

    val userPlant: UserPlant = UserPlant(
        plantId = plant.plantId,
        PlantDate = today.format(formatter), // Format tanggal hari ini
        harvestDate = today.plusDays(harvestDay.toLong()).format(formatter), // Tambah harvestDay
        Method_plant = methodPlant,
        Location_Plant = locationPlant,
        IsComplate = "false"
    )

    val location = when (locationPlant) {
        "tanah" -> "Penanaman di Tanah"
        "hidroponik" -> "Penanaman di Media"
        else -> "Penanaman di Tanah "
    }

    val plantDescription = "${location} - ${methodPlant}"

//    val userPlant: UserPlant = UserPlant(
//        plantId = plant.plantId,
//        PlantDate = {/* ToDO */ },
//        harvestDate = {/* ToDO */ },
//        Method_plant = methodPlant,
//        Location_Plant = locationPlant,
//        IsComplate = "false"
//    )
    val indoFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id"))

    LaunchedEffect(showSuccess.value) {
        if (showSuccess.value) {
            delay(1000)
            showConfirmation.value = false
            onNavigateToNextScreen()
        }
    }

    val navigationEvent = plantViewModel.navigationEvent.collectAsState()
    LaunchedEffect(navigationEvent.value) {
        navigationEvent.value?.let { screen ->
            navController.navigate(screen){
                popUpTo(Screen.AddPlant.route) {
                    inclusive = true
                }
            }
            plantViewModel.resetNavigate()
        }
    }

    AnimatedVisibility(
        visible = showConfirmation.value,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
    }

    AnimatedVisibility(
        visible = showConfirmation.value,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.navigationBars.asPaddingValues())
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (!isLoading.value && !showSuccess.value) {
                                showConfirmation.value = false
                            }
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp)
                    .align(Alignment.BottomCenter)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                // Stops propagation to parent
                            }
                        )
                    }
            ) {
                Spacer(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .fillMaxWidth(0.5f)
                        .height(6.dp)
                        .background(Color(0xFFD9BAFF))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(2.dp, Color(0xFF7C19B9), RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = "${RetrofitClient.STATIC_BASE_URL}${plant.urlPicture}",
                        contentDescription = plant.plantName,
                        loading = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            plant.plantName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            plantDescription,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.W400
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                PlantForecast(
                    leftTitle = "Pada Hari ini",
                    leftDescription = LocalDate.parse(userPlant.PlantDate, formatter)
                        .format(indoFormatter),
                    rightTitle = "Prediksi Panen",
                    rightDescription = LocalDate.parse(userPlant.harvestDate, formatter)
                        .format(indoFormatter),
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Button(
                        onClick = {
                            if (!isLoading.value && !showSuccess.value) {
                                showConfirmation.value = false
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary

                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        enabled = !isLoading.value && !showSuccess.value
                    ) {
                        Text("Batal")
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                plantViewModel.addUserPlant(userPlant)
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = !isLoading.value && !showSuccess.value
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Crossfade(
                                targetState = when {
                                    isLoading.value -> ButtonState.isLoading
                                    else -> ButtonState.Initial
                                },
                                animationSpec = tween(300), label = ""
                            ) { state ->
                                when (state) {
                                    ButtonState.Initial -> Text("Mulai")
                                    ButtonState.isLoading -> CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private enum class ButtonState {
    Initial, isLoading
}