package com.task.core.domain.use_cases

import com.task.core.domain.model.UniversityList
import com.task.core.domain.repository.UniversityRepository
import com.task.core.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UniversityListUseCase @Inject constructor(
    private val universityRepository: UniversityRepository,
) {
    operator fun invoke(): Flow<ResponseState<List<UniversityList>>> = flow {
        try {
            emit(ResponseState.Loading<List<UniversityList>>())
            val news = universityRepository.getAllUniversity().map {
                it.toUniversityList()
            }
            emit(ResponseState.Success<List<UniversityList>>(news))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<UniversityList>>(e.localizedMessage ?: "Error Occurred"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<UniversityList>>("Error Occurred"))
        }
    }
}