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

    val name = "John"

    private val _state = MutableStateFlow<ListViewState>(ListViewState.Idle)
    val state: StateFlow<ListViewState> get() = _state


    init {
        saveListValues()
    }
    fun saveListValues() {
        val names = listOf("John", "Doe")
        viewModelScope.launch {
            saveListValuesUC.invoke(names).collect{ resource ->
                when(resource) {
                    is Resource.Loading -> {
                        logger.info("Loading")
                        _state.value  = ListViewState.Loading
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