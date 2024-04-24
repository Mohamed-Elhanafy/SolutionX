package com.example.solutionx.presentation.screens.list

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.saveList.domain.interactor.SaveListValuesUC
import com.example.solutionx.features.saveList.domain.interactor.TranslateListWorker
import com.example.solutionx.features.saveList.domain.interactor.TranslateListWorker.Companion.KEY_NAMES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    application: Application,
) : AndroidViewModel(
    application
) {

    private val workManager: WorkManager = WorkManager.getInstance(application)

    var workMessage: MutableStateFlow<WorkInfo?> = MutableStateFlow(null)


    fun pressesIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.SaveListValues -> saveListValues(intent)
            is ListIntent.TranslateListValues -> translateListValues(intent.names)
        }
    }

    private fun translateListValues(names: List<String>) {
        val data = workDataOf(
            KEY_NAMES to names.toTypedArray()
        )

        val workRequest = OneTimeWorkRequestBuilder<TranslateListWorker>()
            .setInputData(data).build()

        observeWorkInfo(workRequest)

        workManager.beginUniqueWork(
            WORKER_NAME,
            ExistingWorkPolicy.KEEP,
            workRequest
        ).enqueue()

    }

    private fun observeWorkInfo(workRequest: OneTimeWorkRequest) {

        workMessage.value = workManager.getWorkInfoByIdLiveData(workRequest.id).value

        viewModelScope.launch {
            workManager.getWorkInfoByIdLiveData(workRequest.id).asFlow().collect { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    workMessage.value = workInfo
                    logger.info("workInfo: ${workInfo.outputData.getString(TranslateListWorker.KEY_RESULT_MESSAGE)}")
                    getNamesList()
                }
            }
        }
    }

    private fun saveListValues(intent: ListIntent.SaveListValues) {
        viewModelScope.launch {

            saveListValuesUC.saveNamesList(intent.names)

            getNamesList()
        }
    }


    private fun getNamesList() {
        viewModelScope.launch {
            saveListValuesUC.getNamesList().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val data = resource.data
                        for (name in data) {
                            logger.info(name)
                        }
                    }

                    is Resource.Failure -> {
                        val error = resource.exception
                        logger.error(error.message.toString())
                    }

                    else -> {
                    }
                }
            }
        }
    }


    companion object {
        private val logger = LoggerFactory.getLogger(ListViewModel::class.java)
        const val  WORKER_NAME= "TranslateListWorker"
    }
}