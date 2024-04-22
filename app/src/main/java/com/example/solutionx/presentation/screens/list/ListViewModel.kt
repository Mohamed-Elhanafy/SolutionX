package com.example.solutionx.presentation.screens.list

import am.leon.utilities.android.helpers.logging.LoggerFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.models.Resource
import com.example.solutionx.features.saveList.domain.interactor.SaveListValuesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC
) : ViewModel() {


    private val _state = MutableStateFlow<ListViewState>(ListViewState.Idle)
    val state: StateFlow<ListViewState> get() = _state




    fun pressesIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.SaveListValues -> saveListValues(intent)
            is ListIntent.TranslateListValues -> translateListValues(intent.names)
        }
    }

    private fun translateListValues(names: List<String>) {
        TODO("Not yet implemented")
    }

    private fun saveListValues(intent: ListIntent.SaveListValues) {
        viewModelScope.launch {
            saveListValuesUC.invoke(intent.names).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        logger.info("Loading")
                        _state.value = ListViewState.Loading
                    }

                    is Resource.Success -> {
                        logger.info("Success")
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