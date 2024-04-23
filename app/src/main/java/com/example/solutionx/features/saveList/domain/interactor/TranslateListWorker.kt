package com.example.solutionx.features.saveList.domain.interactor

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.solutionx.features.saveList.domain.repository.ISaveListRepository
import com.example.solutionx.presentation.screens.list.ListViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class TranslateListWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val saveListUC: SaveListValuesUC
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val names = inputData.getStringArray(KEY_NAMES)
            if (names != null) {

                saveListUC.saveNamesList(names.toList())

                Result.success(
                    workDataOf(KEY_RESULT_MESSAGE to "the list has been updated successfully")
                )

            } else {
                logger.error("error in translation list worker")
                Result.failure()
            }
        }


    }


    companion object {
        const val KEY_NAMES = "key_names"
        const val KEY_RESULT_MESSAGE = "key_result_message"
        private val logger = LoggerFactory.getLogger(TranslateListWorker::class.java)
    }
}
