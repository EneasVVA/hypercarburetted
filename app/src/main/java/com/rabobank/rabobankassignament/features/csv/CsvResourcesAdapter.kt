package com.rabobank.rabobankassignament.features.csv

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class CsvResourcesAdapter
@Inject constructor() : RecyclerView.Adapter<CsvResourcesAdapter.ViewHolder>() {
    internal var resources: List<CsvResourceView> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        val changes = oldValue - newValue
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
        val tvFilename: TextView = itemView.findViewById<TextView>(R.id.tv_filename)
        val ivRadio: ImageView = itemView.findViewById(R.id.iv_radio)

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
                if(enable) R.drawable.ic_radio_button_checked else R.drawable.ic_radio_button_unchecked,
                itemView.context.theme)
        )
    }
}