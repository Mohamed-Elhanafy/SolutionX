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
import com.example.solutionx.presentation.screens.login.LoginFragment
import com.example.solutionx.presentation.screens.login.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    //hilt viewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val setNames = view.findViewById<Button>(R.id.set_names)


        setNames.setOnClickListener {


        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observe the state
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is ListViewState.Loading -> {
                        // Show loading
                        logger.info("Loading")
                    }

                    is ListViewState.Success -> {
                        // Handle success
                        val names: List<String> = state.data
                        logger.info(names[0])
                    }

                    is ListViewState.Error -> {
                        // Handle error
                        val error: Throwable = state.error
                        logger.error(error.message.toString())
                    }

                    is ListViewState.Idle -> {
                        // Handle idle
                    }
                }

            }

            }

    }

    private fun handleState() {
        TODO("Not yet implemented")
    }


    companion object {
        private val logger = LoggerFactory.getLogger(LoginFragment::class.java)
    }
}