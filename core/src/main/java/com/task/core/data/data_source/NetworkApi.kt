package com.task.core.data.data_source

import com.task.core.data.dto.UniversityListDTO
import retrofit2.http.GET

interface NetworkApi {

    @GET("/search?country=United+States")
    suspend fun getUniversityList(): UniversityListDTO
}