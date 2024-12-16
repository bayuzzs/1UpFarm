package com.example.oneupfarm.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.data.model.User
import com.example.oneupfarm.ui.component.LogOutDialog
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.viewmodel.AuthViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val showDialog = remember { mutableStateOf(false) }
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
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
            authViewModel.resetNavigate()
        }
    }


    Scaffold(
        topBar = {
            SettingsTopBar(navController)
        },
        containerColor = Color(0xFFF4EFF8)
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AccountCard(
                isLoading = isLoading.value,
                user = user.value,
                modifier = Modifier.padding(16.dp)
            )
            SettingsNavigation(
                modifier = Modifier.padding(16.dp),
                onLogOutClicked = {
                    showDialog.value = true
                }
            )

            if (showDialog.value) {
                LogOutDialog(
                    onDismissRequest = { showDialog.value = false },
                    onLogOut = { authViewModel.logout() })
            }
        }
    }
}

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    user: User? = null,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        if (isLoading || user == null) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().height(96.dp)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD9BAFF)
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(if (user.gender == Gender.M) R.drawable.boyavatar else R.drawable.girlavatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(16.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(3f)
                ) {
                    Text(
                        text = user.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = user.email,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    tint = Color(0xFF7C19B9),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun SettingsNavigation(modifier: Modifier = Modifier, onLogOutClicked: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            SettingsSection(
                iconImage = R.drawable.ic_bell_fill,
                iconTitle = "Notifikasi",
                onClick = {/*TO DO*/ },
                addInfo = "toggle"
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            SettingsSection(
                iconImage = R.drawable.ic_shield_fill,
                iconTitle = "Keamanan Akun",
                onClick = {/*TO DO*/ },
                addInfo = null
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            SettingsSection(
                iconImage = R.drawable.ic_report_fill,
                iconTitle = "Masukan",
                onClick = {/*TO DO*/ },
                addInfo = null
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            SettingsSection(
                iconImage = R.drawable.ic_about_fill,
                iconTitle = "Tentang Kami",
                onClick = {/*TO DO*/ },
                addInfo = null
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            SettingsSection(
                iconImage = R.drawable.ic_logout_fill,
                iconTitle = "Keluar Akun",
                onClick = { onLogOutClicked() },
                addInfo = "next"
            )
        }
    }
}

@Composable
fun SettingsSection(
    @DrawableRes iconImage: Int,
    iconTitle: String,
    onClick: () -> Unit,
    addInfo: String?,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .height(20.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
    ) {
        Icon(
            painter = painterResource(iconImage),
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = iconTitle,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.weight(2f)
        )
        if (addInfo == "toggle") {
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier.weight(1f)
            )
        } else if (addInfo == "next") {
            Icon(
                painter = painterResource(R.drawable.ic_next),
                contentDescription = null,
                tint = Color(0xFF7C19B9),
                modifier = Modifier.weight(1f)
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Pengaturan",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
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