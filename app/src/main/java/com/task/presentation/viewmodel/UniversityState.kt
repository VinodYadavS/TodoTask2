package com.task.presentation.viewmodel

import com.task.core.domain.model.UniversityList

/** This data class is used to handle the isLoading
 * and channelList and error message in viewModel
 */
data class UniversityState(
    val isLoading: Boolean? = false,
    val universityList: List<UniversityList>? = emptyList(),
    val error: String? = "",
)
