package com.example.solutionx.common.presentaion

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.solutionx.R
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.databinding.LoadingViewBinding
import com.example.solutionx.databinding.NoNetworkViewBinding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private lateinit var loadingView: LoadingViewBinding

    protected abstract val bindingClass: Class<Binding>

    private var _binding: Binding? = null

    protected val binding: Binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView()

        loadingView = LoadingViewBinding.inflate(
            inflater,
            binding.root as ViewGroup,
            true
        ) // Inflate loading view

        return binding.root

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


    protected fun showLoading() {
        loadingView.loadingView.visibility = View.VISIBLE
    }

    protected fun hideLoading() {
        loadingView.loadingView.visibility = View.GONE
    }



}