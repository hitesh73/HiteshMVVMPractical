package com.rkoshti.hitesh_practical_mvvm.di

import com.rkoshti.hitesh_practical_mvvm.data.repository.GetUserDataImpl
import com.rkoshti.hitesh_practical_mvvm.domain.repository.GetUserDataRepository
import dagger.*
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSubsRepo(impl: GetUserDataImpl): GetUserDataRepository
}
