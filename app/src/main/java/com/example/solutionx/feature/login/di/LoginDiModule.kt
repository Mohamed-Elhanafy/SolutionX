package com.example.solutionx.feature.login.di

import com.example.solutionx.feature.login.data.local.LocalDataSourceImpl
import com.example.solutionx.feature.login.data.remote.RemoteDataSourceImpl
import com.example.solutionx.feature.login.data.repositoty.UserRepositoryImpl
import com.example.solutionx.feature.login.domain.local.LocalDataSource
import com.example.solutionx.feature.login.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object LoginDiModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(RemoteDataSourceImpl())
    }

    @Provides
    fun provideRemoteDataSource(): RemoteDataSourceImpl {
        return RemoteDataSourceImpl()
    }

    @Provides
    fun providesLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
}
