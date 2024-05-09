package com.example.solutionx

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.solutionx.android.helpers.logger.LoggerFactory
import com.example.solutionx.common.data.models.SolutionXException
import com.example.solutionx.common.presentaion.BaseActivity
import com.example.solutionx.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val bindingClass = ActivityMainBinding::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set up the host fragment and the nav controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.findNavController()

    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        logger.info("onActivityReady")
    }

    override fun viewInit() {

    }


    fun handleHttpException(exception: SolutionXException) {
        when (exception) {
            is SolutionXException.HttpException -> TODO()
            is SolutionXException.IOException -> TODO()
            is SolutionXException.NetworkErrors.NoInternetConnection -> TODO()
            is SolutionXException.NetworkErrors.TimeoutException -> TODO()
            is SolutionXException.NetworkErrors.UnreachableNetwork -> TODO()

            is SolutionXException.NoNetworkConnection -> TODO()
            is SolutionXException.Unknown -> TODO()
        }
    }

    companion object {
     val logger = LoggerFactory.getLogger(MainActivity::class.java)
    }
}



