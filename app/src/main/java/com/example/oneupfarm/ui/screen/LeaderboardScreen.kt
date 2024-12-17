package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.model.LeaderboardUser
import com.example.oneupfarm.ui.component.OUFBottomBar
import com.example.oneupfarm.ui.component.TopLeaderboardCard

@Composable
fun LeaderboardScreen(navController: NavController, modifier: Modifier = Modifier) {
    val topThree = DataSource.dummyLeaderboardUser.slice(0..2)
    val topTen = DataSource.dummyLeaderboardUser.slice(3..9)

    Scaffold(
        bottomBar = { OUFBottomBar(navController = navController) },
        containerColor = Color.White,
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C19B9),
                            Color(0xFF9651E8)
                        )
                    )
                )
        ) {
            LazyColumn(modifier = Modifier.padding(top = 40.dp)) {
                item {
                    Text(
                        text = "Papan Peringkat",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                item {
                    Text(
                        text = "Tingkat Teratas Pengguna",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom,
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        TopLeaderboardCard(3, topThree[2])
                        TopLeaderboardCard(1, topThree[0])
                        TopLeaderboardCard(2, topThree[1])
                    }
                }
                item {
                    LeaderboardLazyColumn(
                        leaderboardUserList = topTen,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LeaderboardLazyColumn(
    leaderboardUserList: List<LeaderboardUser>,
    modifier: Modifier = Modifier
) {
    val num = 4

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(max = 1000.dp)
        ) {
            itemsIndexed(leaderboardUserList) { index, leaderboardUser ->
                val isLast = leaderboardUserList.last() == leaderboardUser
                val currentNum = num + index

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Box(modifier = Modifier.width(32.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = "$currentNum.",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFD9BAFF)
                            ),
                            shape = CircleShape,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(leaderboardUser.avatarImage),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp)
                            )
                        }
                        Text(
                            text = leaderboardUser.name,
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(R.drawable.ic_gold),
                            contentDescription = null,
                            tint = Color(0xFFFFCA28),
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(16.dp)
                        )
                        Text(
                            text = leaderboardUser.goldValue.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9D00),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    if (!isLast) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(Color(0xFFF4EFF8))
                        )
                    }
                }
            }
        }
    }
}