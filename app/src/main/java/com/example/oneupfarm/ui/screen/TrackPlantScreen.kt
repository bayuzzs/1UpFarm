package com.example.oneupfarm.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.api.PlantApi
import com.example.oneupfarm.data.api.RetrofitClient
import com.example.oneupfarm.ui.component.OUFBottomBar
import com.example.oneupfarm.ui.component.TrackPlantCard
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.theme.SurfaceColor
import com.example.oneupfarm.viewmodel.PlantViewModel
import com.example.oneupfarm.viewmodel.PlantViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun TrackPlantScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    var isActivePlantExpanded by remember { mutableStateOf(true) }
    var isCompletePlantExpanded by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val retrofit = remember { RetrofitClient.create(context) }
    val plantApi = remember { retrofit.create(PlantApi::class.java) }
    val plantViewModel: PlantViewModel = viewModel(factory = PlantViewModelFactory(plantApi))

    val userPlants = plantViewModel.userPlants.collectAsState()
    SideEffect {
        scope.launch {
            plantViewModel.getAllUserPlants()
        }
    }

    Scaffold(
        bottomBar = { OUFBottomBar(navController = navController) },
        floatingActionButton = {
            AddPlant(onClick = {
                navController.navigate(Screen.AddPlant.route) {

                }
            })
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .background(SurfaceColor)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.padding_large))
                .padding(top = dimensionResource(R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            item {
                Text(
                    "Lacak Tanaman",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isActivePlantExpanded = !isActivePlantExpanded }
                        .animateContentSize()
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
                    Icon(
                        imageVector = if (isActivePlantExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            item {
                AnimatedVisibility(
                    visible = isActivePlantExpanded,
                    enter = expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(durationMillis = 400)
                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Top,
                        animationSpec = tween(durationMillis = 400)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                    ) {

                        if (userPlants.value.isEmpty()) {
                            Text("Belum Ada tanaman")
                        } else {
                            userPlants.value.forEach { userPlant ->
                                TrackPlantCard(navController = navController, userPlant = userPlant)
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isCompletePlantExpanded = !isCompletePlantExpanded }
                        .animateContentSize()
                ) {
                    Text(
                        "Tanaman Selesai",
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
                    Icon(
                        imageVector = if (isCompletePlantExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }

            item {
                AnimatedVisibility(
                    visible = isCompletePlantExpanded,
                    enter = expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(durationMillis = 400)
                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Top,
                        animationSpec = tween(durationMillis = 400)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
                    ) {
//                        repeat(3) {
//                            TrackPlantCard()
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddPlant(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFFFD0136),
        contentColor = Color.White,
        shape = CircleShape,
        modifier = modifier
            .size(48.dp)
            .border(4.dp, Color.White, CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Tambah",
            modifier = Modifier.size(28.dp)
        )
    }
}
