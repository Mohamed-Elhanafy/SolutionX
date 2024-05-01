package com.example.solutionx.common.presentaion

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.solutionx.R
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.databinding.LoadingViewBinding
import com.example.solutionx.databinding.NoNetworkViewBinding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private lateinit var loadingView: LoadingViewBinding

    protected abstract val bindingClass: Class<Binding>

    protected lateinit var binding: Binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = viewBinding(bindingClass).getValue(this, this::binding)

        loadingView = LoadingViewBinding.inflate(inflater, binding?.root as ViewGroup, true) // Inflate loading view

        return binding.root

    }


    protected fun handleStatusCode(statusCode: Int) {
        when (statusCode) {
            401 -> {
                findNavController().navigate(R.id.action_global_to_loginFragment)
            }

            404 -> {
                // navigate to 404 page
            }

            else -> {
                Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    protected fun handleNetworkError(error: Throwable) {
        if (error is SolutionXException.NetworkErrors.NoInternetConnection) {
            // Handle no internet connection
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
        } else {
            // Handle other types of errors
            Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
        }
    }

    protected fun navigateTo(destination: Int) {
        findNavController().navigate(destination)
    }

    protected fun navigateBack() {
        findNavController().popBackStack()
    }

    protected fun navigateDeepLink(uri: Uri) {
        findNavController().navigate(uri)
    }

    protected fun showLoading() {
        loadingView.loadingView.visibility = View.VISIBLE
    }

    protected fun hideLoading() {
        loadingView.loadingView.visibility = View.GONE
    }

    protected fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}