package com.example.solutionx.feature.login.di

import com.example.solutionx.feature.login.data.remote.RemoteDataSourceImpl
import com.example.solutionx.feature.login.data.repositoty.UserRepositoryImpl
import com.example.solutionx.feature.login.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(RemoteDataSourceImpl())
    }


}
