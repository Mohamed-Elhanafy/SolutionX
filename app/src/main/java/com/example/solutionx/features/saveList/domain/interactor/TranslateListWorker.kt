package com.example.solutionx.features.saveList.domain.interactor

import com.example.solutionx.android.helpers.logger.LoggerFactory
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
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
            try {
                val names = inputData.getStringArray(KEY_NAMES)?.toList()
                if(names.isNullOrEmpty()){
                    Result.failure()
                }else{
                    saveListUC.saveNamesList(names)
                    Result.success(
                        workDataOf(KEY_RESULT_MESSAGE to "the list has been updated successfully")
                    )
                }


            } catch (e: Exception) {
                logger.error("Error in TranslateListWorker: ${e.message}")
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
