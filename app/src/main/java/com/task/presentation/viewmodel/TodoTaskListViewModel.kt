package com.task.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.core.domain.use_cases.TodoTaskListUseCase
import com.task.core.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** This universityList View model is used to fetch
 * the data from the universityListUseCase
 */
@HiltViewModel
class TodoTaskListViewModel @Inject constructor(
    private val todotaskListUseCase: TodoTaskListUseCase,
) : ViewModel() {

    private val _todotaskListValue = MutableStateFlow(TodoTaskState())
    var todoTaskListState: StateFlow<TodoTaskState> = _todotaskListValue

    /** This function is used to get the channel list */
    fun getAllTodoTaskList() = viewModelScope.launch(Dispatchers.IO) {
        todotaskListUseCase().collect {
            when (it) {
                is ResponseState.Loading -> {

                    _todotaskListValue.value = TodoTaskState(isLoading = true)
                }

                is ResponseState.Success -> {
                    _todotaskListValue.value = TodoTaskState(universityList = it.data ?: emptyList())
                }

                is ResponseState.Error -> {

                    _todotaskListValue.value =
                        TodoTaskState(error = it.message ?: "Unexpected Error")
                }
            }
        }
    }
}