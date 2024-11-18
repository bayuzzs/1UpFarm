package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oneupfarm.R
import com.example.oneupfarm.model.ChoosePlant

@Composable
fun ChoosePlantCard(plant: ChoosePlant, selected: Boolean, onClick: () -> Unit = {}) {
    val badge = when (plant.difficulty) {
        "Mudah" -> R.drawable.ic_badge_easy
        "Sedang" -> R.drawable.ic_badge_medium
        else -> R.drawable.ic_badge_easy
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .border(
                width = 4.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFEBE9FF))
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = plant.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    painter = painterResource(badge),
                    contentDescription = "Status",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .width(IntrinsicSize.Min)
                ) {
                    Text(
                        text = plant.difficulty,
                        fontSize = 12.sp,
                    )
                    Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color(0xFF661599))
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = plant.icon),
                contentDescription = plant.name,
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.End)
            )
        }
    }
}