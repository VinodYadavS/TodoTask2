package com.task.core.data.dto

import com.task.core.domain.model.UniversityList

data class UniversityDTOItem(
    val name: String? = "",
    val code: String? = "",
    val country: String? = "",
){
    fun toUniversityList(): UniversityList {
        return UniversityList(
            name = name,
            code = code,
            country = country
        )
    }
}