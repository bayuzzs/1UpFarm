package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oneupfarm.model.NotifType
import com.example.oneupfarm.model.Notification

@Composable
fun NotificationCard(notification: Notification, modifier: Modifier = Modifier) {
    val plantName = notification.plantName
    val notifType = notification.notifType
    var notifTypeString = ""

    if (notifType == NotifType.NOT_TODAY) {
        notifTypeString = notification.timeStamp
    } else {
        notifTypeString = "Hari Ini"
    }

    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(notification.plantImage),
                    contentDescription = notification.plantName,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = notification.content,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "$plantName - $notifTypeString",
                        fontSize = 9.sp,
                        color = Color(0xFF7C19B9)
                    )
                }
            }
        }
        if (notification.seen == false) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF7C19B9), shape = RoundedCornerShape(8.dp))
                    .width(8.dp)
                    .height(80.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}