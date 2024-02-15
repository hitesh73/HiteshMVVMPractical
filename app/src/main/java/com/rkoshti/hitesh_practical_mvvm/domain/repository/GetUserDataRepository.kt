package com.rkoshti.hitesh_practical_mvvm.domain.repository

import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserDataRepository {
    suspend fun getUserDataList(): Flow<Resource<List<UserResponseItem>>>

}