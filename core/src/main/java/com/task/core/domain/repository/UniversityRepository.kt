package com.task.core.domain.repository

import com.task.core.data.dto.UniversityListDTO


interface UniversityRepository {
    suspend fun getAllUniversity(): UniversityListDTO
}