package com.rkoshti.hitesh_practical_mvvm.data.repository

import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.data.remote.ApiService
import com.rkoshti.hitesh_practical_mvvm.domain.repository.GetUserDataRepository
import com.rkoshti.hitesh_practical_mvvm.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserDataImpl @Inject constructor(private val apiService: ApiService) :
    GetUserDataRepository {
    override suspend fun getUserDataList(): Flow<Resource<List<UserResponseItem>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(apiService.getUsers()))
            } catch (exception: Exception) {
                emit(Resource.Error(exception.message.toString()))
            }
        }
    }


}