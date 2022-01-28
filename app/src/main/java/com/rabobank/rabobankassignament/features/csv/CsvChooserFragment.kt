package com.rabobank.rabobankassignament.features.csv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.core.platform.BaseFragment
import com.rabobank.rabobankassignament.databinding.FragmentCsvChooserBinding

class CsvChooserFragment : BaseFragment () {
    private var _binding: FragmentCsvChooserBinding? = null
    private val binding get() = _binding

    override fun layoutId() = R.layout.fragment_csv_chooser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCsvChooserBinding.inflate(inflater, container, false)
        setupCsvParseButtonAction()

        return binding?.root
    }

    private fun setupCsvParseButtonAction() {
        binding?.btnParse?.setOnClickListener {
            findNavController().navigate(R.id.action_CSVChooserFragment_to_CSVParsedFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}