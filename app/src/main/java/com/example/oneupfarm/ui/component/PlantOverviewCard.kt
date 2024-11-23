package com.example.oneupfarm.ui.component

import android.widget.ProgressBar
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.theme.ButtonColor

@Composable
fun PlantOverviewCard(modifier: Modifier = Modifier, onClick: () -> Unit) {
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFB7FCC9))
                    .padding(vertical = 12.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_thumb_up),
                    contentDescription = "Good",
                    tint = Color(0xFF0A7D0C)
                )
                Text("Baik", fontWeight = FontWeight.Bold, color = Color(0xFFC7002A))
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                StepProgressIndicator(steps = 3, completedSteps = 1)
                PlantFlushSection(onClick = onClick)
            }
        }

    }
}

@Composable
fun StepProgressIndicator(
    steps: Int,
    completedSteps: Int,
) {
    Box {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF0A9BC7).copy(alpha = 0.2f))
                .fillMaxWidth()
                .height(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until steps) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(
                            if (i < completedSteps) RoundedCornerShape(50.dp)
                            else if (i == 0) RoundedCornerShape(
                                bottomStart = 50.dp, topStart = 50.dp
                            ) else if (i == steps - 1) RoundedCornerShape(
                                bottomEnd = 50.dp, topEnd = 50.dp
                            ) else RoundedCornerShape(0)
                        )
                        .background(
                            if (i < completedSteps) Color(0xFF0A9BC7)
                            else Color.Transparent,
                        )
                ) {
                    if (i >= completedSteps && i < steps - 1) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .background(Color(0xFF0A9BC7))
                                .size(10.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                }
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_finish),
            contentDescription = "finish",
            tint = Color(0xFFFF9D00),
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 4.dp, y = -(3).dp)
        )
    }
}

@Composable
fun PlantFlushSection(onClick: () -> Unit) {
    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFF0A9BC7).copy(alpha = 0.2f), CircleShape)
                    .weight(1f)
                    .height(24.dp)
            ) {}
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF0A9BC7)),
                contentPadding = PaddingValues(vertical = 3.dp, horizontal = 28.dp),
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            ) {
                Text("Info", fontWeight = FontWeight.Bold)
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_drum),
            contentDescription = "shower",
            tint = Color(0xFFFF9D00),
            modifier = Modifier.offset(x = -(8).dp)
        )
    }
}