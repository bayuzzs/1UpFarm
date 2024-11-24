package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.ui.component.ClaimBadge
import com.example.oneupfarm.ui.component.GardeningCard
import com.example.oneupfarm.ui.component.PlantForecast
import com.example.oneupfarm.ui.component.PlantEducation
import com.example.oneupfarm.ui.component.PlantOverviewCard
import com.example.oneupfarm.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantMonitoringScreen(navController: NavHostController, onClose: () -> Unit) {
    val showGetStarted = remember { mutableStateOf(false) }
    val showTips = remember { mutableStateOf(false) }

    val plantGetstartedItems = DataSource.plantGetstartedItems
    val plantTipsItems = DataSource.plantTipsItems

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Detail Tanaman",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            modifier = Modifier.padding(start = 40.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF7C19B9)
                    )
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(0xFF7C19B9))
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp)
                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            painter = painterResource(R.drawable.lettuce),
                            contentDescription = null,
                            modifier = Modifier
                                .size(156.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(6.dp, Color.White, RoundedCornerShape(16.dp))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp))
                            .fillMaxWidth()
                            .background(Color(0xFFF4EFF8))
                            .padding(24.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(Color(0xFFD9BAFF))
                                .height(8.dp)
                                .fillMaxWidth(0.5f)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Tomat",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Penanaman Tanpa Tanah - Hidroponik",
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        PlantForecast(
                            leftTitle = "Waktu Tanam",
                            leftDescription = "5 Januari 2024",
                            rightTitle = "Waktu Panen",
                            rightDescription = "5 Februari 2024"
                        )
                        Text(
                            text = "Status: Sedang Berjalan",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .fillMaxWidth(0.7f)
                                .background(Color(0xFF7C19B9))
                                .padding(vertical = 3.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Kondisi, Periode & Frekuensi Siraman",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        PlantOverviewCard(onClick = {})
                        Spacer(modifier = Modifier.height(24.dp))
                        ClaimBadge(onClick = {})

                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Langkah Awal",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        GardeningCard(image = R.drawable.get_started_illustration,
                            onClick = { showGetStarted.value = true })

                        Text(
                            text = "Rekomendasi Nutrisi",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        GardeningCard(image = R.drawable.nutrition_recommendation,
                            onClick = { showTips.value = true })

                    }

                }
            }
        }

        PlantEducation(showGetStarted, plantGetstartedItems)
        PlantEducation(showTips, plantTipsItems)
    }
}


@Preview
@Composable
fun PlantMonitoringScreenPreview() {
    PlantMonitoringScreen(navController = rememberNavController(), onClose = {})
}