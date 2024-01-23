package com.task.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.core.domain.use_cases.UniversityListUseCase
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
class UniversityListViewModel @Inject constructor(
    private val universityListUseCase: UniversityListUseCase,
) : ViewModel() {

    private val _universityListValue = MutableStateFlow(UniversityState())
    var universityListState: StateFlow<UniversityState> = _universityListValue

    /** This function is used to get the channel list */
    fun getAllUniversityList() = viewModelScope.launch(Dispatchers.IO) {
        universityListUseCase().collect {
            when (it) {
                is ResponseState.Loading -> {
                    System.out.println("kkkkk"+_universityListValue.value)

                    _universityListValue.value = UniversityState(isLoading = true)
                }

                is ResponseState.Success -> {
                    System.out.println("kkkkk"+_universityListValue.value)
                    _universityListValue.value = UniversityState(universityList = it.data ?: emptyList())
                }

                is ResponseState.Error -> {
                    System.out.println("kkkkk"+_universityListValue.value)

                    _universityListValue.value =
                        UniversityState(error = it.message ?: "Unexpected Error")
                }
            }
        }
    }
}