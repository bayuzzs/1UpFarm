package com.example.oneupfarm.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.model.ChoosePlant
import com.example.oneupfarm.ui.component.AddPlantConfirmation
import com.example.oneupfarm.ui.component.AddPlantOption
import com.example.oneupfarm.ui.component.ChoosePlantCard
import com.example.oneupfarm.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlantScreen(
    navController: NavController,
    onClose: () -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    val steps = remember { listOf("choose_place ", "choose_method", "choose_plant") }
    val selectedChoosePlace = remember { mutableStateOf("soil") }
    val selectedChooseMethod = remember { mutableStateOf("pot") }
    val showConfirmation = remember { mutableStateOf(false) }

    val staticPlantLists = DataSource.staticPlantLists
    val selectedChoosePlant = remember { mutableIntStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tambah Tanaman") },
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            floatingActionButton = {
                Row(
                    horizontalArrangement = if (currentStep > 0) Arrangement.SpaceBetween else Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = if (currentStep > 0) 28.dp else 0.dp)
                ) {
                    if (currentStep > 0) {
                        FloatingActionButton(
                            onClick = { currentStep-- },
                            containerColor = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Previous"
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            if (currentStep < steps.size - 1) currentStep++ else showConfirmation.value =
                                true
                        },
                        containerColor = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ) {
                        Icon(
                            if (currentStep < steps.size - 1) Icons.AutoMirrored.Filled.KeyboardArrowRight else Icons.Default.Check,
                            contentDescription = "Next"
                        )
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                print(currentStep)
                StepProgressIndicator(steps.size, currentStep + 1)

                AnimatedContent(
                    targetState = currentStep,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> fullWidth },
                                animationSpec = tween(durationMillis = 500)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> -fullWidth },
                                animationSpec = tween(durationMillis = 500)
                            )
                        } else {
                            slideInHorizontally(
                                initialOffsetX = { fullWidth -> -fullWidth },
                                animationSpec = tween(durationMillis = 500)
                            ) togetherWith slideOutHorizontally(
                                targetOffsetX = { fullWidth -> fullWidth },
                                animationSpec = tween(durationMillis = 500)
                            )
                        }
                    },
                    label = ""
                ) { currentStep ->
                    when (currentStep) {
                        0 -> ScrollableContent { ChoosePlace(selectedChoosePlace) }
                        1 -> ScrollableContent { ChooseMethod(selectedChooseMethod) }
                        2 -> PlantSelectionScreen(staticPlantLists, selectedChoosePlant)
                    }
                }
            }
        }

        AddPlantConfirmation(showConfirmation, onNavigateToNextScreen = {navController.navigate(Screen.Profile.route)})

    }
}

@Composable
fun ChooseMethod(selectedChooseMethod: MutableState<String>) {
    AddPlantSectionStep(
        title = "Metode apa yang ingin kamu gunakan?",
        illustration = R.drawable.method_illustration
    ) {
        AddPlantOption(
            title = "Pot",
            description = "Cara menanam tanaman dalam wadah pot. Pot bisa diletakkan di mana saja, sangat fleksibel.",
            iconId = R.drawable.ic_pot,
            selected = selectedChooseMethod.value == "pot",
            onClick = { selectedChooseMethod.value = "pot" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AddPlantOption(
            title = "Polybag",
            description = "Metode menanam dalam kantong plastik, polybag menjadi media tanam praktis diruang terbatas.",
            iconId = R.drawable.ic_polybag,
            selected = selectedChooseMethod.value == "polybag",
            onClick = { selectedChooseMethod.value = "polybag" }
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun ChoosePlace(selectedChoosePlace: MutableState<String>) {
    AddPlantSectionStep(
        title = "Dimana kamu ingin menanam?",
        illustration = R.drawable.plant_illustration
    ) {
        AddPlantOption(
            title = "Penanaman di Tanah",
            description = "Menanam menggunakan tanah memberikan akar tanaman akses langsung pada unsur hara alami.",
            iconId = R.drawable.ic_soil,
            selected = selectedChoosePlace.value == "soil",
            onClick = { selectedChoosePlace.value = "soil" }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AddPlantOption(
            title = "Hidroponik",
            description = "Cara menanam menggunakan media air untuk menjadi media tanam.",
            iconId = R.drawable.ic_hydroponic,
            selected = selectedChoosePlace.value == "hydroponic",
            onClick = { selectedChoosePlace.value = "hydroponic" }
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun AddPlantSectionStep(
    title: String,
    @DrawableRes illustration: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Illustration
        Image(
            painter = painterResource(illustration),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        content()
    }
}

@Composable
fun StepProgressIndicator(
    steps: Int,
    completedSteps: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0 until steps) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (i < completedSteps) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    )
            )

            if (i < steps - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun PlantSelectionScreen(plants: List<ChoosePlant>, selectedChoosePlant: MutableState<Int>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        PlantsGrid(plants = plants, selectedChoosePlant)
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = Color.Gray
        )
        Text(
            text = "Pilih Tanaman",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun PlantsGrid(plants: List<ChoosePlant>, selectedChoosePlant: MutableState<Int>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(plants) { index, plant ->
            ChoosePlantCard(
                plant = plant,
                selected = index == selectedChoosePlant.value,
                onClick = { selectedChoosePlant.value = index }
            )
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun ScrollableContent(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        content()
    }
}

//@Preview
//@Composable
//fun PlantingScreenPreview() {
//    PlantingScreen(onClose = {})
//}

