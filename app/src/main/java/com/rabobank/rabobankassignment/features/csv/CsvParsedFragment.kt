package com.rabobank.rabobankassignment.features.csv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabobank.rabobankassignment.R
import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.extension.failure
import com.rabobank.rabobankassignment.core.extension.showErrorDialog
import com.rabobank.rabobankassignment.core.platform.BaseFragment
import com.rabobank.rabobankassignment.databinding.FragmentCsvParsedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CsvParsedFragment :
    BaseFragment<FragmentCsvParsedBinding>(FragmentCsvParsedBinding::inflate) {
    private val arguments: CsvParsedFragmentArgs by navArgs()

    @Inject
    lateinit var csvSheetAdapter: CsvSheetAdapter

    private val csvParsedViewModel: CsvParsedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(csvParsedViewModel) {
            failure(failure, ::renderFailure)
        }
    }

    private fun renderFailure(failure: Failure?) {
        animationLoader?.hideLoading()
        when (failure) {
            is Failure.CsvGetDataError -> context?.showErrorDialog(R.string.error_getting_csv_data)
            else -> context?.showErrorDialog(R.string.unknown_error)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        readCsvResource()
    }

    private fun readCsvResource() {
        lifecycleScope.launch {
            animationLoader?.showLoading()
            csvParsedViewModel.loadCsv(arguments.csvResource).collectLatest { pagingData ->
                animationLoader?.hideLoading()
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