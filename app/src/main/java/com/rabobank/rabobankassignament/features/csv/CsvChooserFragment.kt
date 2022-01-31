package com.rabobank.rabobankassignament.features.csv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.core.extension.observe
import com.rabobank.rabobankassignament.core.platform.BaseFragment
import com.rabobank.rabobankassignament.databinding.FragmentCsvChooserBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CsvChooserFragment : BaseFragment () {
    private var _binding: FragmentCsvChooserBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var csvResourcesAdapter: CsvResourcesAdapter

    private val csvChooserViewModel : CsvChooserViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_csv_chooser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(csvChooserViewModel) {
            observe(resources, ::renderCsvResourcesList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCsvChooserBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadResourcesList()
    }

    private fun initializeView() {
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
                CsvChooserFragmentDirections.actionCSVChooserFragmentToCSVParsedFragment(csvResource = it.resource)
                findNavController().navigate(R.id.action_CSVChooserFragment_to_CSVParsedFragment)
            }
        }
    }

    private fun renderCsvResourcesList(list: List<CsvResourceView>?) {
        csvResourcesAdapter.resources = list.orEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}