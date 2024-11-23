package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.theme.ButtonColor

@Composable
fun ClaimBadge(onClick: () -> Unit) {
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