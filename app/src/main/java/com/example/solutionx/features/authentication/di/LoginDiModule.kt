package com.example.solutionx.features.authentication.di

import com.example.solutionx.features.authentication.data.repositoty.local.LocalDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.remote.RemoteDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.UserRepositoryImpl
import com.example.solutionx.features.authentication.domain.interactor.LoginWithEmailUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithSocialUC
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import com.example.solutionx.features.authentication.domain.repository.UserRepository
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
internal object LoginDiModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(RemoteDataSourceImpl() , LocalDataSourceImpl())
    }

    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Provides
    fun providesLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }


    @Provides
    fun provideLoginWithEmailUC(userRepository: UserRepository): LoginWithEmailUC {
        return LoginWithEmailUC(userRepository)
    }

    @Provides
    fun provideLoginWithPhoneUC(userRepository: UserRepository): LoginWithPhoneUC {
        return LoginWithPhoneUC(userRepository)
    }

    @Provides
    fun provideLoginWithSocialUC(userRepository: UserRepository): LoginWithSocialUC {
        return LoginWithSocialUC(userRepository)
    }
}
