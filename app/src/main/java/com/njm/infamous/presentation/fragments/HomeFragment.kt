package com.njm.infamous.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.njm.infamous.presentation.ui.components.HomeMain
import com.njm.infamous.presentation.viewModel.SerieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(){

    private val serieViewModel: SerieViewModel by viewModel()

    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeMain(findNavController(), serieViewModel)
            }
        }
    }

}