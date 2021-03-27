package com.njm.infamous.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.presentation.ui.components.DetailSerieMain
import com.njm.infamous.presentation.ui.components.DetailSerieScreenContent
import com.njm.infamous.presentation.viewModel.SerieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailSerieFragment : Fragment() {
    private val serieViewModel: SerieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DetailSerieMain(
                    arguments?.getParcelable<Result>("serie") as Result,
                    findNavController(), serieViewModel
                )
            }
        }
    }
}