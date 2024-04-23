package com.example.solutionx.presentation.screens.list

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.solutionx.R
import com.example.solutionx.features.login.domain.models.LoginResponse
import com.example.solutionx.features.saveList.domain.interactor.TranslateListWorker
import com.example.solutionx.presentation.screens.login.LoginFragment
import com.example.solutionx.presentation.screens.login.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val setNames = view.findViewById<Button>(R.id.set_names)
        val translateNames = view.findViewById<Button>(R.id.update_list)


        setNames.setOnClickListener {
            val intent = ListIntent.SaveListValues(listOf("mohamed", "ahmed","hamed"))
            viewModel.pressesIntent(intent)
        }

        translateNames.setOnClickListener {
            val intent = ListIntent.TranslateListValues(listOf("محمد", "احمد","حامد"))
            viewModel.pressesIntent(intent)

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.workMessage.collect { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    val resultMessage = workInfo.outputData.getString(TranslateListWorker.KEY_RESULT_MESSAGE)
                    logger.info("workInfo: $resultMessage")
                }
            }
        }

    }




    companion object {
        private val logger = LoggerFactory.getLogger(LoginFragment::class.java)
    }
}