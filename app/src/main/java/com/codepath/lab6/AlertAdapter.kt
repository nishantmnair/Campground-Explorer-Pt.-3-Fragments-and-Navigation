package com.codepath.lab6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlertAdapter(private val context: Context, private val alerts: List<Alert>) :
    RecyclerView.Adapter<AlertAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_alert, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(alerts[position])
    }

    override fun getItemCount() = alerts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val alertCategoryTextView: TextView = itemView.findViewById(R.id.alertCategory)
        private val alertTitleTextView: TextView = itemView.findViewById(R.id.alertTitle)
        private val alertDescriptionTextView: TextView = itemView.findViewById(R.id.alertDescription)

        fun bind(alert: Alert) {
            alertCategoryTextView.text = alert.category
            alertTitleTextView.text = alert.title
            alertDescriptionTextView.text = alert.description
        }
    }
}
