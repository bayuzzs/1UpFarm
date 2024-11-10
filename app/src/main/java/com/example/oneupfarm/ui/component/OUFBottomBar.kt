package com.example.oneupfarm.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun OUFBottomBar(navController: NavController ,modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(1) }
    val items = listOf("Profile", "Plants", "Calendar", "Store")

    Column(modifier = Modifier.height(IntrinsicSize.Min)) {
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
                    selected = Screen.Profile.route == navController.currentDestination?.route,
                    navigationOnClick = {navController.navigate(Screen.Profile.route)  }
                )
                NavItem(
                    DataSource.navigationItems[1],
                    selected = Screen.Login.route == navController.currentDestination?.route,
                    navigationOnClick = { navController.navigate(Screen.Login.route) }
                )
                Spacer(modifier = Modifier)
                NavItem(
                    DataSource.navigationItems[2],
                    selected = false,
                    navigationOnClick = { }
                )
                NavItem(
                    DataSource.navigationItems[3],
                    selected = false,
                    navigationOnClick = { }
                )
            }

            // Floating Button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .offset(y = (-28).dp)
                    .border(width = 4.dp, color = Color.White, shape = CircleShape)
                    .background(color = Color(0xFF7C19B9), shape = CircleShape)
                    .padding(4.dp)
                    .align(Alignment.Center)
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_plant),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
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