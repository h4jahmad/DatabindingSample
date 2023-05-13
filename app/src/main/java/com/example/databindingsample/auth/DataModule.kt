package com.example.databindingsample.auth

import com.example.databindingsample.auth.repository.UserRepository
import com.example.databindingsample.auth.repository.UserRepositoryImpl
import com.example.databindingsample.auth.usecase.LoginUseCase
import com.example.databindingsample.auth.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}