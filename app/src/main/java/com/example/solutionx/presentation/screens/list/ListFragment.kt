package com.example.solutionx.presentation.screens.list

import com.example.solutionx.android.helpers.logger.LoggerFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.common.presentaion.BaseFragment
import com.example.solutionx.databinding.FragmentListBinding
import com.example.solutionx.features.saveList.domain.interactor.TranslateListWorker
import com.example.solutionx.presentation.screens.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    private val viewModel: ListViewModel by viewModels()

    override val bindingClass = FragmentListBinding::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setNames.setOnClickListener {
            val intent = ListIntent.SaveListValues(listOf("mohamed", "ahmed","hamed"))
            viewModel.pressesIntent(intent)

        }

        binding.updateList.setOnClickListener {
            showLoading()
            val intent = ListIntent.TranslateListValues(listOf("محمد", "احمد","حامد"))
            viewModel.pressesIntent(intent)
        }

        collectWorkMessage()

    }



    private fun collectWorkMessage() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.workMessage.collect { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    hideLoading()
                    val resultMessage =
                        workInfo.outputData.getString(TranslateListWorker.KEY_RESULT_MESSAGE)
                    logger.info("workInfo: $resultMessage")
                    Toast.makeText(requireActivity(), resultMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    companion object {
        private val logger = LoggerFactory.getLogger(LoginFragment::class.java)
    }
}