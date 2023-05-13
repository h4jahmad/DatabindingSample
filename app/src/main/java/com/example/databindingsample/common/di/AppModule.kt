package com.example.databindingsample.common.di

import com.example.databindingsample.common.data.local.UserDataSource
import com.example.databindingsample.common.data.local.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDataProvider(): UserDataSource = UserLocalDataSourceImpl()
}