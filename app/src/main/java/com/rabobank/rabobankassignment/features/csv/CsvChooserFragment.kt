package com.rabobank.rabobankassignment.features.csv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabobank.rabobankassignment.R
import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.extension.failure
import com.rabobank.rabobankassignment.core.extension.observe
import com.rabobank.rabobankassignment.core.extension.showErrorDialog
import com.rabobank.rabobankassignment.core.platform.BaseFragment
import com.rabobank.rabobankassignment.databinding.FragmentCsvChooserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CsvChooserFragment :
    BaseFragment<FragmentCsvChooserBinding>(FragmentCsvChooserBinding::inflate) {

    @Inject
    lateinit var csvResourcesAdapter: CsvResourcesAdapter

    private val csvChooserViewModel: CsvChooserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(csvChooserViewModel) {
            observe(resources, ::renderCsvResourcesList)
            failure(failure, ::renderFailure)
        }
    }

    private fun renderFailure(failure: Failure?) {
        animationLoader?.hideLoading()

        when (failure) {
            is Failure.CsvResourcesCannotBeListedFailure -> context?.showErrorDialog(R.string.error_loading_csv_resources)
            else -> context?.showErrorDialog(R.string.unknown_error)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadResourcesList()
    }

    private fun initializeView() {
        animationLoader?.showLoading()
        setUpResourcesList()
        setListenerCsvParseButton()
    }

    private fun loadResourcesList() = csvChooserViewModel.loadResources()

    private fun setUpResourcesList() {
        binding?.csvResourcesList?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = csvResourcesAdapter
            csvResourcesAdapter.clickListener = csvChooserViewModel::setResourceSelection
        }
    }

    private fun setListenerCsvParseButton() {
        binding?.btnParse?.setOnClickListener {
            csvChooserViewModel.getResourceSelected()?.let {
                findNavController().navigate(
                    CsvChooserFragmentDirections.actionCSVChooserFragmentToCSVParsedFragment(
                        it.resource
                    )
                )
            }
        }
    }

    private fun renderCsvResourcesList(list: List<CsvResourceView>?) {
        csvResourcesAdapter.resources = list.orEmpty()
        animationLoader?.hideLoading()
    }
}