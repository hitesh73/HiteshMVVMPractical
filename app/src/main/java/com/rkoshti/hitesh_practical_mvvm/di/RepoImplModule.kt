package com.rkoshti.hitesh_practical_mvvm.di

import com.rkoshti.hitesh_practical_mvvm.data.remote.ApiService
import com.rkoshti.hitesh_practical_mvvm.data.repository.GetUserDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoImplModule {

    @Provides
    @Singleton
    fun provideGetUserDataRepoImpl(apiService: ApiService) = GetUserDataImpl(apiService)
}