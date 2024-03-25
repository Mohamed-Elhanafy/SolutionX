package com.example.solutionx.features.authentication.di

import android.content.Context
import com.example.solutionx.features.authentication.data.repositoty.remote.network.LoginApi
import com.example.solutionx.features.authentication.data.repositoty.local.LocalDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.remote.RemoteDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.LoginRepositoryImpl
import com.example.solutionx.features.authentication.data.repositoty.local.UserPreferences
import com.example.solutionx.features.authentication.domain.interactor.LoginWithEmailUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithSocialUC
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import com.example.solutionx.features.authentication.domain.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
internal object LoginDiModule {

    @Provides
    fun provideUserRepository(
        loginApi: LoginApi,
        userPreferences: UserPreferences
    ): LoginRepository {
        return LoginRepositoryImpl(
            RemoteDataSourceImpl(loginApi),
            LocalDataSourceImpl(userPreferences)
        )
    }

    @Provides
    fun provideRemoteDataSource(loginApi: LoginApi): RemoteDataSource {
        return RemoteDataSourceImpl(loginApi)
    }

    @Provides
    fun providesLocalDataSource(
        userPreferences: UserPreferences
    ): LocalDataSource {
        return LocalDataSourceImpl(userPreferences)
    }

    //provide user preferences
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }


    @Provides
    fun provideLoginWithEmailUC(loginRepository: LoginRepository): LoginWithEmailUC {
        return LoginWithEmailUC(loginRepository)
    }

    @Provides
    fun provideLoginWithPhoneUC(
        loginRepository: LoginRepository,
    ): LoginWithPhoneUC {
        return LoginWithPhoneUC(loginRepository)
    }

    @Provides
    fun provideLoginWithSocialUC(loginRepository: LoginRepository): LoginWithSocialUC {
        return LoginWithSocialUC(loginRepository)
    }
}
