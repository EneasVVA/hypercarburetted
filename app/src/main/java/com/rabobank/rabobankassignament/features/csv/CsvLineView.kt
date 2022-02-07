package com.rabobank.rabobankassignament.features.csv

import androidx.recyclerview.widget.DiffUtil

data class CsvLineView(val position: String, val columns: List<String>?) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CsvLineView>() {
            override fun areItemsTheSame(oldItem: CsvLineView, newItem: CsvLineView): Boolean {
                return oldItem.position == newItem.position
            }

            override fun areContentsTheSame(oldItem: CsvLineView, newItem: CsvLineView): Boolean {
                return oldItem == newItem
            }
        }
    }
}
