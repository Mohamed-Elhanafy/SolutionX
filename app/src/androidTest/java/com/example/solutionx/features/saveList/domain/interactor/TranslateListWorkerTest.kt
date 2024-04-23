package com.example.solutionx.features.saveList.domain.interactor

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.Configuration
import androidx.work.ListenableWorker
import org.junit.Assert.*
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.solutionx.features.saveList.domain.interactor.TranslateListWorker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RuntimeEnvironment
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TranslateListWorkerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context
    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory


    @Before
    fun setup() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext<Context>()
        WorkManagerTestInitHelper.initializeTestWorkManager(context)

        val configuration = Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build()

        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
    }

    @Test
    fun testTranslateListWorkerWithValidArgsAndUpdateTheList() = runBlocking {
        // Define input data
        val inputData = workDataOf(
            TranslateListWorker.KEY_NAMES to arrayOf("محمد", "احمد","حامد")
        )

        // Create a OneTimeWorkRequest for TranslateListWorker
        val request = OneTimeWorkRequestBuilder<TranslateListWorker>()
            .setInputData(inputData)
            .build()

        // Build the worker
        val worker = TestListenableWorkerBuilder.from(context, request).build()


        // Start the worker
        val result = worker.startWork().get()

        // Assert that the worker result is successful
        assertEquals(result, ListenableWorker.Result.success())

        // Get the WorkInfo
        val workInfo = WorkManager.getInstance(context).getWorkInfoById(request.id).get()

        // Assert that the WorkInfo state is SUCCEEDED
        assertEquals(workInfo.state, WorkInfo.State.SUCCEEDED)

        // Get the output data
        val outputData = workInfo.outputData

        // Assert that the output data contains the expected result message
        assertEquals(outputData.getString(TranslateListWorker.KEY_RESULT_MESSAGE), "the list has been updated successfully")
    }
}