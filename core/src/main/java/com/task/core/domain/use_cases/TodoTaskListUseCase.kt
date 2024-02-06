package com.task.core.domain.use_cases

import com.task.core.domain.model.TodoTaskList
import com.task.core.domain.repository.TodoTaskRepository
import com.task.core.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TodoTaskListUseCase @Inject constructor(
    private val todoTaskRepository: TodoTaskRepository,
) {
    operator fun invoke(): Flow<ResponseState<List<TodoTaskList>>> = flow {
        try {
            emit(ResponseState.Loading<List<TodoTaskList>>())
            val todoTasks = todoTaskRepository.getAllTodoTasks().map {
                it.toTaskList()
            }
            emit(ResponseState.Success<List<TodoTaskList>>())
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<TodoTaskList>>(e.localizedMessage ?: "Error Occurred"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<TodoTaskList>>("Error Occurred"))
        }
    }
}