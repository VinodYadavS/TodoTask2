package com.task.core.data.dto

import com.task.core.domain.model.TodoTaskList


data class TodoTaskDTOItem(
    val title: String? = "",
    val status: Boolean? = false
){
    fun toTaskList(): TodoTaskList {
        return TodoTaskList(
            title = title,
          status = status
        )
    }
}