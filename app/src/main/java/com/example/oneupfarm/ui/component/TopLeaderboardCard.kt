package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oneupfarm.R
import com.example.oneupfarm.model.LeaderboardUser

@Composable
fun TopLeaderboardCard(numRank: Int, leaderboardUser: LeaderboardUser, modifier: Modifier = Modifier) {
    val numRankColor: Color = when (numRank) {
        1 -> Color(0xFFFFB415)
        2 -> Color(0xFF9D9D9D)
        else -> Color(0xFFE67600)
    }
    val avatarTopPadding: Int = if (numRank == 1) 40 else 32

    Box(modifier = modifier) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    top = avatarTopPadding.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 32.dp
                )
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD9BAFF)
                    )
                ) {
                    Image(
                        painter = painterResource(leaderboardUser.avatarImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(12.dp)
                    )
                }
                Text(
                    text = leaderboardUser.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_gold),
                        contentDescription = null,
                        tint = Color(0xFFFFCA28),
                        modifier = Modifier.padding(end = 4.dp).size(16.dp)
                    )
                    Text(
                        text = leaderboardUser.goldValue.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9D00),
                        fontSize = 12.sp
                    )
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = numRankColor,
                contentColor = Color.White
            ),
            shape = CircleShape,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopCenter)
        ) {
            Box(modifier = Modifier.aspectRatio(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = numRank.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        LevelBadge(
            level = leaderboardUser.level.toString(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 40.dp)
        )
    }
}

@Composable
fun LevelBadge(level: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.leaderboard_badge),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-4).dp)
        ) {
            Text(
                text = "LEVEL",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 8.sp
            )
            Text(
                text = level,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}