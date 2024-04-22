package com.example.solutionx.features.saveList.di

import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.features.saveList.data.repository.SaveListRepository
import com.example.solutionx.features.saveList.data.repository.local.SaveListLocalDataSource
import com.example.solutionx.features.saveList.domain.interactor.SaveListValuesUC
import com.example.solutionx.features.saveList.domain.repository.ISaveListRepository
import com.example.solutionx.features.saveList.domain.repository.local.ISaveListLocalDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object SaveListDiModule {

    @Provides
    fun provideSaveListRepository(
        localDataSource: ISaveListLocalDataSource
    ): ISaveListRepository {
        return SaveListRepository(localDataSource)
    }

    @Provides
    fun providesLocalDataSource(
        keyValueStorage: KeyValueStorage
    ): ISaveListLocalDataSource {
        return SaveListLocalDataSource(keyValueStorage, Gson())
    }

    @Provides
    fun provideSaveListValuesUC(saveListRepository: ISaveListRepository): SaveListValuesUC {
        return SaveListValuesUC(saveListRepository)
    }
}

/*@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideSaveListValuesUC(
        saveListRepository: ISaveListRepository
    ): SaveListValuesUC {
        return SaveListValuesUC(saveListRepository)
    }
}*/
