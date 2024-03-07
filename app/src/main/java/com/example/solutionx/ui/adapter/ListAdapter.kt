package com.example.solutionx.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.data.model.Country
import com.example.solutionx.data.model.Currency
import com.example.solutionx.data.model.Filter

class ListAdapter(
    private val dataList: List<ListItem>,
    private val onItemClick: (ListItem) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val isCheckedImageView: ImageView = itemView.findViewById(R.id.is_checked)

        fun bind(item: ListItem) {
            nameTextView.text = item.name
            when (item.isSelected) {
                true -> nameTextView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.myBlue
                    )
                )

                false -> nameTextView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.black
                    )
                )
            }

            isCheckedImageView.visibility = if (item.isSelected) View.VISIBLE else View.GONE


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_single, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            // Update the selected property of the clicked item and all other items
            for (i in dataList.indices) {
                dataList[i].isSelected = i == position
            }
            // Notify the adapter that the data has changed
            notifyDataSetChanged()

            onItemClick(item)
        }

    }
}


