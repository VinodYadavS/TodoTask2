package com.task.core.data.repository

import com.task.core.data.data_source.NetworkApi
import com.task.core.data.dto.TodoTaskListDTO
import com.task.core.domain.repository.TodoTaskRepository
import javax.inject.Inject

class TodoTaskRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
) : TodoTaskRepository {




    override suspend fun getAllTodoTasks(): TodoTaskListDTO {
        return networkApi.getTodoTaskList()
    }
}