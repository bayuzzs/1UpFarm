package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.theme.ButtonColor

@Composable
fun TrackPlantCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_small))
            .clip(RoundedCornerShape(12.dp)), elevation = CardDefaults.cardElevation(
            4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(dimensionResource(R.dimen.padding_small))
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            CardImage()

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                CardInformation(
                    name = "Selada",
                    status = "2 Minggu",
                    condition = "Sedang"
                )
                CardButton()
            }

        }
    }
}

@Composable
fun CardImage() {
    Image(
        painter = painterResource(R.drawable.lettuce),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun CardInformation(name: String, status: String, condition: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Status: $status", style = MaterialTheme.typography.labelMedium, color = Color.Gray
    )
    Spacer(modifier = Modifier.height(8.dp))
    PlantLabel(condition)
}

@Composable
fun PlantLabel(condition: String) {
    val textColor: Color = getPlantLabelColor(condition)
    val backgroundColor: Color = getPlantLabelBackgroundColor(condition)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = condition,
            style = MaterialTheme.typography.labelMedium,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun getPlantLabelColor(condition: String): Color {
    return when (condition) {
        "Sedang" -> Color(0xFF9D6806)
        "Baik" -> Color(0xFF0A7D0C)
        "Buruk" -> Color(0xFFC7002A)
        else -> Color.Gray
    }
}

fun getPlantLabelBackgroundColor(condition: String): Color {
    return when (condition) {
        "Sedang" -> Color(0xFFFFEEB7)
        "Baik" -> Color(0xFFB7FCC9)
        "Buruk" -> Color(0xFFF7A6AE)
        else -> Color.Gray
    }
}

@Composable
fun CardButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(ButtonColor),
        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 20.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
    ) {
        Text(
            text = "Lihat",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
    }
}