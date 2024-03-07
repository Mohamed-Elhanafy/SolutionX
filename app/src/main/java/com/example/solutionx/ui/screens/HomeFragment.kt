package com.example.solutionx.ui.screens

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.solutionx.R
import com.example.solutionx.ui.adapter.ListAdapter
import com.example.solutionx.data.mock.countriesList


class HomeFragment : Fragment() {

    private val countries = countriesList
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        setupRv(recyclerView)

    }

    private fun setupRv(recyclerView: RecyclerView?) {
        val listAdapter = ListAdapter(countries) { item ->

            logger.info("Item clicked: $item")
        }

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter

        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(HomeFragment::class.java)
    }
}