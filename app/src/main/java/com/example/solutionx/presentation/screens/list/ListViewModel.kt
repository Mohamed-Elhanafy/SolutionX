package com.example.solutionx.presentation.screens.list

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
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
    //private val workManager: WorkManager,
    application: Application,
) : AndroidViewModel(
    application
) {

    private val workManager: WorkManager = WorkManager.getInstance(application)

    private val _state = MutableStateFlow<ListViewState>(ListViewState.Idle)


    val state: StateFlow<ListViewState> get() = _state


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
            .setInputData(data)
            .build()

        val workInfo: LiveData<WorkInfo> = workManager.getWorkInfoByIdLiveData(workRequest.id)

        logger.info("workInfo: $workInfo")


        workManager.beginUniqueWork(
            "TranslateListWorker",
            ExistingWorkPolicy.KEEP,
            workRequest
        ).enqueue()



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
                    is Resource.Loading -> {
                        logger.info("Loading")
                        _state.value = ListViewState.Loading
                    }

                    is Resource.Success -> {
                        logger.info("Success")
                        logger.info(resource.data.size.toString())
                        _state.value = ListViewState.Success(resource.data)
                    }

                    is Resource.Failure -> {
                        logger.error("Error")
                        _state.value = ListViewState.Error(resource.exception)
                    }
                }
            }
        }
    }


    companion object {
        private val logger = LoggerFactory.getLogger(ListViewModel::class.java)
    }
}