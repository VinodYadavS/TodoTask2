package com.task.core.domain.repository

import com.task.core.data.dto.TodoTaskListDTO


interface TodoTaskRepository {
    suspend fun getAllTodoTasks(): TodoTaskListDTO
}