package com.example.oneupfarm.ui.screen

import android.util.Log
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.data.api.StaticFileApi
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.data.model.User
import com.example.oneupfarm.model.Badge
import com.example.oneupfarm.ui.component.BadgeCard
import com.example.oneupfarm.ui.component.OUFBottomBar
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.utils.staticRetrofit
import com.example.oneupfarm.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading = authViewModel.isLoading.collectAsState()
    val user = authViewModel.user.collectAsState()
    val token = authViewModel.token.collectAsState()
    val navigationEvent = authViewModel.navigationEvent.collectAsState()

    LaunchedEffect(token) {
        if (token.value == null) {
            navController.navigate(Screen.Welcome.route)
            return@LaunchedEffect
        }
        authViewModel.getUserInfo()
//        Log.i("USER INFO", user.toString())
    }

    LaunchedEffect(navigationEvent.value) {
        navigationEvent.value?.let { screen ->
            navController.navigate(screen){
                popUpTo(Screen.Profile.route) {
                    inclusive = true
                }
            }
            authViewModel.resetNavigate()
        }
    }



    Scaffold(
        bottomBar = {
            OUFBottomBar(
                navController = navController,
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        if (isLoading.value || user.value == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.size(96.dp))
            }
        }  else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    ProfileTopBar(navController = navController)
                }
                item {
                    CharacterDetail(user = user.value, modifier = Modifier.padding(horizontal = 16.dp))
                }
                item {
                    StatisticTab(modifier = Modifier.padding(horizontal = 16.dp))
                }
                item {
                    BadgeTab(DataSource.dummyBadge, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Composable
fun CharacterDetail(user: User?,modifier: Modifier = Modifier) {
    val health = user?.avatar?.health?.toFloat() ?: 0f
    val maxHealth = user?.avatar?.maxHealth?.toFloat() ?: 1f
    val progress = if (maxHealth > 0) health / maxHealth else 0f
    Surface(
        modifier = modifier.padding(bottom = 16.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        color = Color.White
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AvatarCard(user?.gender)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                IconWithProgress(
                    iconRes = R.drawable.ic_heart,
                    iconTint = Color(0xFFFD0136),
                    hasProgressIndicator = true,
                    progressValue = progress
                )
                IconWithProgress(
                    iconRes = R.drawable.staricon,
                    iconTint = Color(0xFF0A9BC7),
                    hasProgressIndicator = true,
                    progressValue = user?.avatar?.exp?.toFloat() ?: 0f
                )
                IconWithProgress(
                    iconRes = R.drawable.ic_gold,
                    iconTint = Color(0xFFFFCA28),
                    hasProgressIndicator = false,
                    progressValue = user?.avatar?.gold?.toFloat() ?: 0f
                )
            }
        }
    }
}

@Composable
fun AvatarCard(gender: Gender? = Gender.M) {
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
                painter = painterResource(if (gender == Gender.M) R.drawable.boyavatar else R.drawable.girlavatar),
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
fun StatisticTab(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Statistik",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
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
fun BadgeTab(
    badgeList: List<Badge>,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val columns = when {
        screenWidthDp < 600 -> 3
        screenWidthDp < 840 -> 4
        else -> 6
    }

    Column(modifier = modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Lencana",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
        )
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(max = 1000.dp)
            ) {
                items(badgeList) { badge ->
                    BadgeCard(badge)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(modifier: Modifier = Modifier, navController: NavController) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF7C19B9),
                    Color(0xFF9651E8)
                )
            )
        ),
        title = {
            Text(
                text = "Ayo merawat tanaman!",
                color = Color.White,
                fontSize = 16.sp
            )
        },
        navigationIcon = {
            Text(
                text = "Halooo!",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 12.dp, top = 24.dp)
            )
        },
        actions = {
            IconButton(
                onClick = {/*TO DO*/ },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_game),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {/*TO DO*/ },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_bell),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { navController.navigate(Screen.Settings.route) },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_gear),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}