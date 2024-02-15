package com.rkoshti.hitesh_practical_mvvm.domain.usecase

import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.domain.repository.GetUserDataRepository
import com.rkoshti.hitesh_practical_mvvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserDataUseCase @Inject constructor(private val repository: GetUserDataRepository) {

    suspend fun getUserDataList(): Flow<Resource<List<UserResponseItem>>> {
        return repository.getUserDataList()
    }
}