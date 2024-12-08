package com.example.oneupfarm.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.oneupfarm.R
import com.example.oneupfarm.model.ToDo
import com.example.oneupfarm.ui.component.ToDoCard
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.ui.viewmodel.ToDoViewModel

@Composable
fun ToDoScreen(navController: NavController, modifier: Modifier = Modifier) {
    val toDoViewModel: ToDoViewModel = viewModel()
    val toDoList by toDoViewModel.toDoList
    val NotDoneToDoList = toDoList.filter { !it.completed }
    val DoneToDoList = toDoList.filter { it.completed }
    val lastDoneToDo = if (DoneToDoList.isNotEmpty()) DoneToDoList.last() else null

    Scaffold(
        floatingActionButton = { ToDoBottomBar(navController = navController) },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color(0xFFF4EFF8)
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier.padding(innerPadding)
        ) {
            item {
                ToDoHeader(
                    header = "Daftar Tugas",
                    subHeaderBold = "Tips keren: ",
                    subHeader = "Pastikan suhu ruangan tidak terlalu panas atau dingin. Jangan lupa selalu menjaga sirkulasi udara."
                )
            }

            if (NotDoneToDoList.isNotEmpty()) {
                items(NotDoneToDoList) { toDo ->
                    ToDoCard(
                        toDo = toDo,
                        onTaskClick = { taskId ->
                            toDoViewModel.updateTask(taskId, toDo.id)
                        },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            } else {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Text(
                            text = "Tugas Kosong",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }

            item {
                ToDoHeader(
                    header = "Tugas Selesai",
                    subHeaderBold = "Selamat! ",
                    subHeader = "Kamu sudah selangkah terdepan merawat bumi menjadi lebih hijau."
                )
            }

            if (DoneToDoList.isNotEmpty()) {
                items(DoneToDoList) { toDo ->
                    ToDoCard(
                        toDo = toDo,
                        onTaskClick = { taskId ->
                            toDoViewModel.updateTask(taskId, toDo.id)
                        },
                        modifier = Modifier.padding(
                            top = 8.dp,
                            bottom = if (toDo == lastDoneToDo) 80.dp else 8.dp
                        )
                    )
                }
            } else {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Text(
                            text = "Tidak ada tugas yang selesai",
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ToDoHeader(
    header: String,
    subHeaderBold: String,
    subHeader: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = header,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(subHeaderBold)
                }

                append(subHeader)
            },
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun ToDoBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { navController.popBackStack() },
        containerColor = Color.White,
        contentColor = Color(0xFF7C19B9),
        modifier = Modifier
            .offset(y = (-16).dp)
            .clip(CircleShape)
            .border(2.dp, Color(0xFF7C19B9), shape = CircleShape)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cross),
            contentDescription = null,
            tint = Color(0xFF7C19B9)
        )
    }
}