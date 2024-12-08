package com.example.oneupfarm.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.oneupfarm.data.DataSource
import com.example.oneupfarm.model.ToDo

class ToDoViewModel: ViewModel() {
    private val _toDoList = mutableStateOf(DataSource.dummyToDos)
    val toDoList: State<List<ToDo>> = _toDoList

    fun updateTask(taskId: Int, toDoId: Int) {
        _toDoList.value = _toDoList.value.map { toDo ->
            if (toDo.id == toDoId) {
                toDo.copy(
                    taskList = toDo.taskList.map { task ->
                        if (task.id == taskId) {
                            task.copy(checked = !task.checked)
                        } else {
                            task
                        }
                    }.toMutableList()
                )
            } else {
                toDo
            }
        }
    }
}