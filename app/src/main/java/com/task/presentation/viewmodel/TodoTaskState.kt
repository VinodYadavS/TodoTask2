package com.task.presentation.viewmodel

import com.task.core.domain.model.TodoTaskList

/** This data class is used to handle the isLoading
 * and channelList and error message in viewModel
 */
data class TodoTaskState(
    val isLoading: Boolean? = false,
    val todoTaskList: List<TodoTaskList>? = emptyList(),
    val error: String? = "",
)
