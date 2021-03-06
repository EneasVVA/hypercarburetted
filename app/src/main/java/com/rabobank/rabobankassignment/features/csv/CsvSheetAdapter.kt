package com.rabobank.rabobankassignment.features.csv

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rabobank.rabobankassignment.R
import com.rabobank.rabobankassignment.core.extension.inflate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CsvSheetAdapter constructor(
    diffCallback: DiffUtil.ItemCallback<CsvLineView> = CsvLineView.diffCallback,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    workerDispatcher: CoroutineDispatcher = Dispatchers.Default
    ): PagingDataAdapter<CsvLineView, CsvSheetAdapter.ViewHolder>(diffCallback, mainDispatcher, workerDispatcher) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.csv_row))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(csvLineView = getItem(position))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val csvRowContainer =
            itemView.findViewById<LinearLayoutCompat>(R.id.csv_row_container)
        private val csvCellPosition = itemView.findViewById<TextView>(R.id.csv_cell_position)
        private val csvColumns = mutableListOf<TextView>()

        fun bind(csvLineView: CsvLineView?) {
            csvCellPosition.text = csvLineView?.position
            csvLineView?.columns?.forEachIndexed { index, cellContent ->
                getColumn(index).apply {
                    text = cellContent
                }
            } ?: getColumn(0).run {
                text = resources.getString(R.string.csv_row_cannot_be_read)
            }
        }

        private fun getColumn(index: Int) = csvColumns.getOrElse(index) {
            csvRowContainer
                .inflate<TextView>(R.layout.csv_cell)
                .also(csvRowContainer::addView)
                .also(csvColumns::add)
        }


    }
}