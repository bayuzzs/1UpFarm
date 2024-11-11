package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.R
import com.example.oneupfarm.ui.component.PlantCard
import com.example.oneupfarm.ui.theme.SurfaceColor

@Composable
fun TrackPlantScreen(navController: NavController= rememberNavController(), modifier: Modifier = Modifier) {
//    Scaffold(
//        bottomBar = {
//            OUFBottomBar(modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()))
//        },
//        modifier = Modifier.fillMaxSize()
//    ) { innerPadding ->
//
//    }
    Column(
        modifier = modifier
            .background(SurfaceColor)
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large))
    ) {
        Text(
            "Lacak Tanaman",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Tanaman Aktif",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFD9BAFF))
            )
        }
        PlantCardList()
    }
}

@Composable
fun PlantCardList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(10) {
            PlantCard()
        }
    }
}