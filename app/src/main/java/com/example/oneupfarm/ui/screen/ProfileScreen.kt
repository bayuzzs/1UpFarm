package com.example.oneupfarm.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.theme.OneUpFarmTheme


class ProfilePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen()
        setTheme(R.style.Theme_OneUpFarm)

        setContent {
            OneUpFarmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        WelcomeMessage()
        CharacterDetail()
        StatisticTab()
    }
}

@Composable
fun WelcomeMessage() {
    Surface(
        color = Color(0xFF9651E8),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                top = 60.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Halooo!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                ) {
                    IconButton(onClick = {/*TO DO*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.gameicon),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {/*TO DO*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.bellicon),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {/*TO DO*/ }) {
                        Icon(
                            painter = painterResource(R.drawable.gearicon),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
            Text(
                text = "Ayo merawat tanaman!",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CharacterDetail() {
    Surface(
        modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        color = Color.White
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AvatarCard()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                IconWithProgress(
                    iconRes = R.drawable.hearticon,
                    iconTint = Color(0xFFFD0136),
                    hasProgressIndicator = true,
                    progressValue = 0.7f
                )
                IconWithProgress(
                    iconRes = R.drawable.staricon,
                    iconTint = Color(0xFF0A9BC7),
                    hasProgressIndicator = true,
                    progressValue = 0.3f
                )
                IconWithProgress(
                    iconRes = R.drawable.goldicon,
                    iconTint = Color(0xFFFFCA28),
                    hasProgressIndicator = false,
                    progressValue = 1000.0f
                )
            }
        }
    }
}

@Composable
fun AvatarCard() {
    Box {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD9BAFF)
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.boyavatar),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp)
            )
        }
        Surface(
            color = Color(0xFF7C19B9),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "LV. 1",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 12.dp, end = 12.dp)
            )
        }
        Surface(
            color = Color(0xFF7C19B9),
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.pencilicon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {/*TO DO*/ }
            )
        }
    }
}

@Composable
fun IconWithProgress(
    @DrawableRes iconRes: Int,
    iconTint: Color,
    hasProgressIndicator: Boolean,
    progressValue: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        if (hasProgressIndicator) {
            Box(
                modifier = Modifier
                    .weight(4f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(iconTint.copy(alpha = 0.3f))
            ) {
                LinearProgressIndicator(
                    progress = { progressValue },
                    color = iconTint,
                    trackColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        } else {
            Text(
                text = progressValue.toInt().toString(),
                fontSize = 16.sp,
                color = iconTint,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(4f)
            )
        }
    }
}

@Composable
fun StatisticTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Statistik",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Produktivitas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                //Ini placeholder buat statistik ntar, untuk sekarang pake gambar doang yah
                Image(
                    painter = painterResource(R.drawable.barchart),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun BadgeTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Lencana",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {

        }
    }
}