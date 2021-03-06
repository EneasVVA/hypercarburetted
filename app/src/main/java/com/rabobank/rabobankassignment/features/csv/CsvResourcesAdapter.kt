package com.rabobank.rabobankassignment.features.csv

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.rabobank.rabobankassignment.R
import com.rabobank.rabobankassignment.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class CsvResourcesAdapter
@Inject constructor() : RecyclerView.Adapter<CsvResourcesAdapter.ViewHolder>() {
    internal var resources: List<CsvResourceView> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        val changes = newValue - oldValue
        changes.forEach { csvResourceView ->
            notifyItemChanged(resources.indexOfFirst { csvResourceView.resource == it.resource })
        }
    }

    internal var clickListener: (CsvResourceView) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_csv_resource))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(resources[position], clickListener)
    }

    override fun getItemCount() = resources.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFilename: TextView = itemView.findViewById(R.id.tv_filename)
        private val ivRadio: ImageView = itemView.findViewById(R.id.iv_radio)

        fun bind(csvResourceView: CsvResourceView, clickListener: (CsvResourceView) -> Unit) {
            tvFilename.text = csvResourceView.filename
            checkRadio(csvResourceView.selected)

            itemView.setOnClickListener {
                clickListener(csvResourceView)
            }
        }

        private fun checkRadio(enable: Boolean) = ivRadio.setImageDrawable(
            ResourcesCompat.getDrawable(
                itemView.resources,
                if (enable) R.drawable.ic_radio_button_checked else R.drawable.ic_radio_button_unchecked,
                itemView.context.theme
            )
        )
    }
}