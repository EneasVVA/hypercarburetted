package com.rabobank.rabobankassignament.features.csv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabobank.rabobankassignament.core.platform.BaseFragment
import com.rabobank.rabobankassignament.databinding.FragmentCsvParsedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CsvParsedFragment : BaseFragment<FragmentCsvParsedBinding>(FragmentCsvParsedBinding::inflate) {
    private val arguments: CsvParsedFragmentArgs by navArgs()

    @Inject
    lateinit var csvSheetAdapter: CsvSheetAdapter

    private val csvParsedViewModel: CsvParsedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        readCsvResource()
    }

    private fun readCsvResource() {
        lifecycleScope.launch {
            csvParsedViewModel.loadCsv(arguments.csvResource).collectLatest { pagingData ->
                csvSheetAdapter.submitData(pagingData)
            }
        }
    }

    private fun initializeView() {
        binding?.csvSpreadSheet?.run {
            adapter = csvSheetAdapter
            binding?.csvSpreadSheet?.layoutManager = LinearLayoutManager(context)
        }
    }
}