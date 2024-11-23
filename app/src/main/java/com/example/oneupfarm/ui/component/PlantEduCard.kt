package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oneupfarm.R
import com.example.oneupfarm.model.PlantEducation

@Composable
fun PlantEduCard(plantEducation: PlantEducation) {
    Column {
        Box {
            Image(
                painter = painterResource(plantEducation.image),
                contentDescription = plantEducation.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = plantEducation.chips,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 12.dp, y = 12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White)
                    .padding(vertical = 4.dp)
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopStart)
            )
        }

        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = plantEducation.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = plantEducation.subTitle,
                fontSize = 14.sp,
            )

            Spacer(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD9BAFF))
                    .height(6.dp)
                    .fillMaxWidth(0.7f)
            )

            Text(
                text = plantEducation.description,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp
            )
        }
    }
}