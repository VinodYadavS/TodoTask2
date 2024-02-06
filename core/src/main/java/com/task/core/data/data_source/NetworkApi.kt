package com.task.core.data.data_source

import com.task.core.data.dto.TodoTaskListDTO
import retrofit2.http.GET

interface NetworkApi {

    @GET("/todos/")
    suspend fun getTodoTaskList(): TodoTaskListDTO
}