package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddPlantOption(
    title: String,
    description: String,
    iconId: Int,
    selected: Boolean ,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(
            topStart = 100.dp,
            bottomStart = 100.dp,
            topEnd = 8.dp,
            bottomEnd = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                interactionSource = remember  { MutableInteractionSource() },
                indication = null
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(start = 12.dp)) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .background(if (selected) Color.White else MaterialTheme.colorScheme.surface)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}