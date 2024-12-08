package com.example.oneupfarm.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.oneupfarm.R
import com.example.oneupfarm.model.Task
import com.example.oneupfarm.model.ToDo

@Composable
fun ToDoCard(toDo: ToDo, onTaskClick: (taskId: Int) -> Unit, modifier: Modifier = Modifier) {
    var isCardExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = if (isCardExpanded) BorderStroke(2.dp, Color(0xFF7C19B9)) else null,
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isCardExpanded) 6.dp else 0.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { isCardExpanded = !isCardExpanded }
            ) {
                Image(
                    painter = painterResource(toDo.plantImage),
                    contentDescription = toDo.plantName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .aspectRatio(1f)
                )
                Text(
                    text = toDo.plantName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .background(Color(0xFFD9BAFF))
                )
                Icon(
                    imageVector = if (isCardExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color(0xFF7C19B9)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF7C19B9),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(32.dp)
                        .aspectRatio(1f)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = toDo.taskList.size.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = isCardExpanded,
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
                        .padding(top = 4.dp)
                        .animateContentSize()
                ) {
                    toDo.taskList.forEach { task ->
                        TaskSection(
                            task = task,
                            onClick = { onTaskClick(task.id) },
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskSection(task: Task, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val checked: Boolean = task.checked
    val textColor: Color = if (checked) Color.Black.copy(alpha = 0.5f) else Color.Black
    val goldColor: Color = if (checked) Color(0xFFFFCA28).copy(alpha = 0.5f) else Color(0xFFFFCA28)
    val expColor: Color = if (checked) Color(0xFF0A9BC7).copy(alpha = 0.5f) else Color(0xFF0A9BC7)

    Column(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(if (checked) R.drawable.ic_checkcircle_checked else R.drawable.ic_checkcircle_unchecked),
                contentDescription = null,
                tint = Color(0xFF7C19B9)
            )
            Text(
                text = task.desc,
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 24.dp, top = 4.dp)
        ) {
            if (task.goldValue != null) {
                val goldValue: String = task.goldValue.toString()
                Icon(
                    painter = painterResource(R.drawable.ic_gold),
                    contentDescription = null,
                    tint = goldColor,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "+$goldValue GOLD",
                    color = goldColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            if (task.expValue != null) {
                val expValue: String = task.expValue.toString()
                Icon(
                    painter = painterResource(R.drawable.staricon),
                    contentDescription = null,
                    tint = expColor,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "+$expValue EXP",
                    color = expColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}