package com.example.solutionx.features.authentication.di

import android.content.Context
import com.example.solutionx.common.data.remote.RetrofitApi
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
import com.google.gson.Gson
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
        retrofitApi: RetrofitApi,
        keyValueStorage: KeyValueStorage
    ): LoginRepository {
        return LoginRepositoryImpl(
            RemoteDataSourceImpl(retrofitApi),
            LocalDataSourceImpl(keyValueStorage, Gson())
        )
    }

    @Provides
    fun provideRemoteDataSource(retrofitApi: RetrofitApi): RemoteDataSource {
        return RemoteDataSourceImpl(retrofitApi)
    }

    @Provides
    fun providesLocalDataSource(
        keyValueStorage: KeyValueStorage
    ): LocalDataSource {
        return LocalDataSourceImpl(keyValueStorage, Gson())
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
