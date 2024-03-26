package com.example.solutionx.features.authentication.di

import android.content.Context
import com.example.solutionx.features.authentication.data.repositoty.remote.LoginApi
import com.example.solutionx.features.authentication.data.repositoty.local.LocalDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.remote.RemoteDataSourceImpl
import com.example.solutionx.features.authentication.data.repositoty.LoginRepositoryImpl
import com.example.solutionx.features.authentication.data.storage.DataStoreStorage
import com.example.solutionx.features.authentication.domain.interactor.LoginWithEmailUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.features.authentication.domain.interactor.LoginWithSocialUC
import com.example.solutionx.features.authentication.domain.repository.local.LocalDataSource
import com.example.solutionx.features.authentication.domain.repository.LoginRepository
import com.example.solutionx.features.authentication.domain.repository.local.KeyValueStorage
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
        keyValueStorage: KeyValueStorage
    ): LoginRepository {
        return LoginRepositoryImpl(
            RemoteDataSourceImpl(loginApi),
            LocalDataSourceImpl(keyValueStorage)
        )
    }

    @Provides
    fun provideRemoteDataSource(loginApi: LoginApi): RemoteDataSource {
        return RemoteDataSourceImpl(loginApi)
    }

    @Provides
    fun providesLocalDataSource(
        keyValueStorage: KeyValueStorage
    ): LocalDataSource {
        return LocalDataSourceImpl(keyValueStorage)
    }

    //provide user preferences
    @Provides
    fun provideDataStoreStorage(@ApplicationContext context: Context): KeyValueStorage {
        return DataStoreStorage(context)
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
