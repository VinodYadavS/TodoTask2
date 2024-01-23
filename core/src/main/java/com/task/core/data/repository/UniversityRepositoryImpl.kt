package com.task.core.data.repository

import com.task.core.data.data_source.NetworkApi
import com.task.core.data.dto.UniversityListDTO
import com.task.core.domain.repository.UniversityRepository
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
) : UniversityRepository {


    override suspend fun getAllUniversity(): UniversityListDTO {
        return networkApi.getUniversityList()
    }
}