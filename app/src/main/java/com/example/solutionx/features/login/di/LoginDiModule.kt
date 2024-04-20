package com.example.solutionx.features.login.di

import android.content.Context
import com.example.solutionx.android.helpers.security.KeystoreUtils
import com.example.solutionx.common.data.repository.remote.RetrofitApi
import com.example.solutionx.features.login.data.repositoty.local.LocalDataSource
import com.example.solutionx.features.login.data.repositoty.remote.RemoteDataSource
import com.example.solutionx.features.login.data.repositoty.LoginRepository
import com.example.solutionx.common.data.repository.local.DataStoreStorage
import com.example.solutionx.features.login.domain.interactor.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.LoginWithSocialUC
import com.example.solutionx.features.login.domain.repository.local.ILocalDataSource
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.domain.repository.remote.IRemoteDataSource
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
        retrofitApi: IRestApiNetworkProvider,
        keyValueStorage: KeyValueStorage
    ): ILoginRepository {
        return LoginRepository(
            RemoteDataSource(retrofitApi),
            LocalDataSource(keyValueStorage, Gson() , KeystoreUtils
            )
        )
    }

    @Provides
    fun provideRemoteDataSource(retrofitApi: IRestApiNetworkProvider): IRemoteDataSource {
        return RemoteDataSource(retrofitApi)
    }

    @Provides
    fun providesLocalDataSource(
        keyValueStorage: KeyValueStorage
    ): ILocalDataSource {
        return LocalDataSource(keyValueStorage, Gson() , KeystoreUtils)
    }

    //provide user preferences
    @Provides
    fun provideDataStoreStorage(@ApplicationContext context: Context): KeyValueStorage {
        return DataStoreStorage.getInstance(context)
    }


    @Provides
    fun provideLoginWithEmailUC(ILoginRepository: ILoginRepository): LoginWithEmailUC {
        return LoginWithEmailUC(ILoginRepository)
    }

    @Provides
    fun provideLoginWithPhoneUC(
        loginRepository: ILoginRepository,
    ): LoginWithPhoneUC {
        return LoginWithPhoneUC(loginRepository)
    }

    @Provides
    fun provideLoginWithSocialUC(ILoginRepository: ILoginRepository): LoginWithSocialUC {
        return LoginWithSocialUC(ILoginRepository)
    }


}
