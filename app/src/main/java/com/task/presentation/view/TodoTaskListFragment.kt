package com.task.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.R
import com.task.core.domain.model.TodoTaskList
import com.task.core.uicomponents.BaseFragment
import com.task.databinding.TodoTaskListBinding
import com.task.presentation.adapter.TodoTaskListAdapter
import com.task.presentation.viewmodel.TodoTaskListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/** This Fragment is used for
 * showing the list of UniversityList*/
@AndroidEntryPoint
class TodoTaskListFragment : BaseFragment<TodoTaskListBinding>() {
    private val viewModel: TodoTaskListViewModel by viewModels()
    private lateinit var recyclerAdapter: TodoTaskListAdapter
    override fun getFragmentView() = R.layout.todo_task_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeData()
        callApi()
    }

    /** This is to set up the recyclerview */
    private fun setUpRecyclerView() {
        recyclerAdapter = TodoTaskListAdapter(requireContext(), arrayListOf())
        binding.universityListRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
    }

    /** This function is used to observe the data
     * from view model flows Coroutinescope with dispatcher main thread*/
    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.todoTaskListState.collectLatest {
                when {
                    it.isLoading == true -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    it.error?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    it.universityList?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                        recyclerAdapter.updateTodoTaskList(it.universityList as ArrayList<TodoTaskList>)
                    }
                }
            }
        }
    }

    /** This function is used to call the api
     * and get the all UniversityList*/
    private fun callApi() {
        viewModel.getAllTodoTaskList()
    }
}