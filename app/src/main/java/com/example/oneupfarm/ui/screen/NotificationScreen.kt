package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.model.NotifType
import com.example.oneupfarm.model.Notification
import com.example.oneupfarm.ui.component.NotificationCard

@Composable
fun NotificationScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { NotificationTopAppBar(navController = navController) },
        containerColor = Color(0xFFF4EFF8)
    ) { innerPadding ->
        NotificationLazyList(notificationList = DataSource.dummyNotif, modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationTopAppBar(navController: NavController, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Notifikasi",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF4EFF8)
        )
    )
}

@Composable
fun NotificationLazyList(notificationList: List<Notification>, modifier: Modifier = Modifier) {
    val groupedNotification = notificationList.groupBy { it.notifType }
    LazyColumn(modifier = modifier) {
        groupedNotification.forEach { (notifType, notifications) ->
            item {
                Text(
                    text = when(notifType) {
                        NotifType.TODAY -> "Hari ini"
                        NotifType.NOT_TODAY -> "Sebelumnya"
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            items(notifications) { notifications ->
                NotificationCard(notifications, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }
    }
}