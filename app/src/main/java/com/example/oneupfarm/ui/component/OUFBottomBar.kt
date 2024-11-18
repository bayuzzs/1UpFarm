package com.example.oneupfarm.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.model.NavigationItem
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.navigation.getCurrentRoute

@Composable
fun OUFBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    val currentRoute = navController.getCurrentRoute()

    Column(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Box(
            modifier = modifier
                .background(Color.Black)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                NavItem(
                    DataSource.navigationItems[0],
                    selected = currentRoute == Screen.Profile.route,
                    navigationOnClick = { navController.navigate(Screen.Profile.route) }
                )
                NavItem(
                    DataSource.navigationItems[1],
                    selected = currentRoute == Screen.TrackPlant.route,
                    navigationOnClick = { navController.navigate(Screen.TrackPlant.route) }
                )
                Spacer(modifier = Modifier)
                NavItem(
                    DataSource.navigationItems[2],
                    selected = currentRoute == Screen.Calendar.route,
                    navigationOnClick = { navController.navigate(Screen.Calendar.route) }
                )
                NavItem(
                    DataSource.navigationItems[3],
                    selected = currentRoute == Screen.MarketPlace.route,
                    navigationOnClick = { navController.navigate(Screen.MarketPlace.route) }
                )
            }

            // Floating Button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .offset(y = (-28).dp)
                    .background(color = Color(0xFF7C19B9), shape = CircleShape)
                    .border(width = 4.dp, color = Color.White, shape = CircleShape)
                    .padding(3.dp)
                    .align(Alignment.Center)
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_plant),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NavItem(
    navigationItem: NavigationItem,
    selected: Boolean,
    navigationOnClick: () -> Unit
) {
    IconButton(onClick = navigationOnClick) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(IntrinsicSize.Min)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(if (selected) navigationItem.selectedIcon else navigationItem.icon),
                contentDescription = null,
                tint = if (selected) Color(0xFF7C19B9) else Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            if (selected) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(
                            color = Color(0xFF7C19B9),
                            shape = RoundedCornerShape(50)
                        )
                        .height(4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}