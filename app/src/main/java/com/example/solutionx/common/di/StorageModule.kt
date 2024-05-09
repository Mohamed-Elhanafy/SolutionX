package com.example.solutionx.common.di

import android.content.Context
import com.example.solutionx.common.data.repository.local.DataStoreStorage
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideDataStoreStorage(@ApplicationContext context: Context): KeyValueStorage {
        return DataStoreStorage(context)
    }
}