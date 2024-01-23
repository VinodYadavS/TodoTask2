package com.task.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.R
import com.task.core.domain.model.UniversityList
import com.task.core.uicomponents.BaseFragment
import com.task.databinding.FragmentNewsListBinding
import com.task.presentation.adapter.UniversityListAdapter
import com.task.presentation.viewmodel.UniversityListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/** This Fragment is used for
 * showing the list of UniversityList*/
@AndroidEntryPoint
class UniversityListFragment : BaseFragment<FragmentNewsListBinding>() {
    private val viewModel: UniversityListViewModel by viewModels()
    private lateinit var recyclerAdapter: UniversityListAdapter
    override fun getFragmentView() = R.layout.university_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeData()
        callApi()
    }

    /** This is to set up the recyclerview */
    private fun setUpRecyclerView() {
        recyclerAdapter = UniversityListAdapter(requireContext(), arrayListOf())
        binding.universityListRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }
    }

    /** This function is used to observe the data
     * from view model flows Coroutinescope with dispatcher main thread*/
    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.universityListState.collectLatest {
                when {
                    it.isLoading == true -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    it.error?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                    }

                    it.universityList?.isNotEmpty() == true -> {
                        binding.progressBar.visibility = View.GONE
                        recyclerAdapter.updateUniversityList(it.universityList as ArrayList<UniversityList>)
                    }
                }
            }
        }
    }

    /** This function is used to call the api
     * and get the all UniversityList*/
    private fun callApi() {
        viewModel.getAllUniversityList()
    }
}