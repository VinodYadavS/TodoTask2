package com.task

import com.task.core.domain.model.TodoTaskList
import com.task.core.domain.use_cases.TodoTaskListUseCase
import com.task.core.util.ResponseState
import com.task.presentation.viewmodel.TodoTaskListViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by VI20218870 on 06,February,2024
 */
@RunWith(JUnit4::class)
class TodoTaskListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val dispatcherRule=DispatcherRule()


    private lateinit var todoTaskListUseCase:TodoTaskListUseCase
    private lateinit var todoTaskListViewModel: TodoTaskListViewModel

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        todoTaskListUseCase= mockk(relaxed = true)
        todoTaskListViewModel= TodoTaskListViewModel(todoTaskListUseCase)
    }

    @After
    fun tearDown(){
        clearAllMocks()
    }
    @OptIn(ExperimentalCoroutinesApi::class )
    @Test
    fun test_loading_state()= runTest {
        coEvery { todoTaskListUseCase.invoke() }returns flow {
            emit(ResponseState.Loading<List<TodoTaskList>>())
        }
        todoTaskListViewModel.getAllTodoTaskList()

        todoTaskListViewModel.todoTaskListState.collect(){
            Assert.assertTrue(it.isLoading!!)
            Assert.assertNull(it.error)
            Assert.assertTrue(it.todoTaskList!!.isEmpty())
        }

    }

    @Test
    fun fetch_data_sucess()= runTest {
      
       /* val listData =listOf("1",1,"task1","task2")


        coEvery { todoTaskListUseCase.invoke() }returns flow {
            emit(ResponseState.Success(listData))
        }*/
        todoTaskListViewModel.getAllTodoTaskList()

        todoTaskListViewModel.todoTaskListState.collect(){
            Assert.assertFalse(it.isLoading!!)
            Assert.assertNull(it.error)
        }

    }

}