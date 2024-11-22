package com.example.oneupfarm.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.component.PlantForecast
import com.example.oneupfarm.ui.theme.ButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantMonitoringScreen(navController: NavHostController, onClose: () -> Unit) {
    Box {
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
                        containerColor = Color.Transparent
                    )
                )
            },
            containerColor = Color(0xFF7C19B9)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                ) {
                    Image(
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.lettuce), contentDescription = null,
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
                    PlantForecast("5 Januari 2024", "5 Februari 2024")
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

                    PlantOverviewCard()

                    ClaimBadge(onClick = {})

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Langkah Awal",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    GardeningCard(image = R.drawable.get_started_illustration)

                    Text(
                        text = "Rekomendasi Nutrisi",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    GardeningCard(image = R.drawable.nutrition_recommendation)

                }

            }
        }


    }
}

@Composable
fun PlantOverviewCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFB7FCC9))
                    .padding(vertical = 12.dp)
                    .padding(horizontal = 18.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_thumb_up),
                    contentDescription = "Good",
                    tint = Color(0xFF0A7D0C)
                )
                Text("Baik", fontWeight = FontWeight.Bold, color = Color(0xFFC7002A))
            }
        }
    }
}

@Composable
fun ClaimBadge(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .drawBehind {
                drawRoundRect(
                    color = Color(0xFF7C19B9),
                    style = Stroke(
                        width = 3f,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(20f, 20f),
                            0f
                        )
                    ),
                    cornerRadius = CornerRadius(
                        8.dp.toPx()
                    )
                )
            }
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_badge_easy),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text("Penguasaan Mudah", fontWeight = FontWeight.Bold)
            Text("Selesaikan tugas untuk mendapatkan lencana ini.")
        }
        Button(
            onClick = onClick,
            enabled = false,
            colors = ButtonDefaults.buttonColors(ButtonColor),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 20.dp),
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
        ) {
            Text("Klaim", color = Color.White)
        }
    }
}

@Composable
fun GardeningCard(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = "Person planting",
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .fillMaxWidth()
                    .aspectRatio(16f / 6f),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                // Text Content
                Text(
                    text = "Langkah Awal Penanaman",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Ayo! baca bagaimana cara menanam tanaman mulai dari awal",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onClick,
                    contentPadding = PaddingValues(vertical = 6.dp, horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF661599)
                    ),
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier
                        .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                        .align(Alignment.End)
                ) {
                    Text(
                        text = "Lihat",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PlantMonitoringScreenPreview() {
    PlantMonitoringScreen(navController = rememberNavController(), onClose = {})
}