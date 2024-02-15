package com.rkoshti.hitesh_practical_mvvm.presenatation.home_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.domain.usecase.GetUserDataUseCase
import com.rkoshti.hitesh_practical_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUserDataUseCase: GetUserDataUseCase) :
    ViewModel() {

    private val _fetchList: MutableStateFlow<Resource<List<UserResponseItem>>?> =
        MutableStateFlow(null)
    val fetchList = _fetchList

    init {
        viewModelScope.launch { fetchUserData() }
    }

    private suspend fun fetchUserData() {
        getUserDataUseCase.getUserDataList().collect {
            _fetchList.value = it
        }
    }
}