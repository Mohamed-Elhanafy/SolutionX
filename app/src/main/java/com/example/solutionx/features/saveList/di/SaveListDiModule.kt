package com.example.solutionx.features.saveList.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.saveList.data.repository.SaveListRepository
import com.example.solutionx.features.saveList.data.repository.local.LocalDataSource
import com.example.solutionx.features.saveList.domain.interactor.SaveListValuesUC
import com.example.solutionx.features.saveList.domain.repository.ISaveListRepository
import com.example.solutionx.features.saveList.domain.repository.local.ILocalDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SaveListDiModule {
    @Singleton
    @Provides
    fun provideSaveListRepository(
        localDataSource: ILocalDataSource
    ): ISaveListRepository {
        return SaveListRepository(localDataSource)
    }
    @Singleton
    @Provides
    fun providesLocalDataSource(
        keyValueStorage: KeyValueStorage
    ): ILocalDataSource {
        return LocalDataSource(keyValueStorage, Gson())
    }

    @Singleton
    @Provides
    fun provideSaveListValuesUC(saveListRepository: ISaveListRepository): SaveListValuesUC {
        return SaveListValuesUC(saveListRepository)
    }
    @Provides
    @Singleton

    fun provideWorkManga(@ApplicationContext context: Context, factory: HiltWorkerFactory ): WorkManager {

        val configuration = Configuration.Builder().setWorkerFactory(factory).build()
        WorkManager.initialize(context, configuration)

        return WorkManager.getInstance(context)
    }
}

