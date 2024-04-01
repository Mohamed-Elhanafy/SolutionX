package com.example.solutionx.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solutionx.R
import com.example.solutionx.features.singleSelection.data.model.Country
import com.example.solutionx.features.singleSelection.data.model.Currency
import com.example.solutionx.features.singleSelection.data.model.Filter


class ListAdapter<T>(
    private val dataList: List<T>,
    private val onItemClick: (T) -> Unit
) : RecyclerView.Adapter<ListAdapter.BaseViewHolder<T>>() {
    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    class CurrencyViewHolder(itemView: View) : BaseViewHolder<Currency>(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val codeTextView: TextView = itemView.findViewById(R.id.code)
        private val signTextView: TextView = itemView.findViewById(R.id.sign)
        private val isCheckedImageView: ImageView = itemView.findViewById(R.id.is_checked)

        override fun bind(item: Currency) {
            // Bind data to views for Currency model
            nameTextView.text = item.name
            codeTextView.text = item.code
            signTextView.text = item.sign
            isCheckedImageView.visibility = if (item.isSelected) View.VISIBLE else View.GONE

            when (item.isSelected) {

                true -> {
                    nameTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )
                    codeTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )
                    signTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )

                }

                false -> {

                }
            }
        }
    }

    class FilterViewHolder(itemView: View) : BaseViewHolder<Filter>(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val isCheckedImageView: ImageView = itemView.findViewById(R.id.is_checked)

        override fun bind(item: Filter) {
            nameTextView.text = item.name
            if (item.isSelected) {
                nameTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.myBlue))
            }

            isCheckedImageView.visibility = if (item.isSelected) View.VISIBLE else View.GONE


        }
    }

    class CountryViewHolder(itemView: View) : BaseViewHolder<Country>(itemView) {
        private val flagTextView: TextView = itemView.findViewById(R.id.flag)
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val currencyTextView: TextView = itemView.findViewById(R.id.currency)
        private val phoneCodeTextView: TextView = itemView.findViewById(R.id.phone_code)
        private val isCheckedImageView: ImageView = itemView.findViewById(R.id.is_checked)

        override fun bind(item: Country) {
            flagTextView.text = item.flag
            nameTextView.text = item.name
            currencyTextView.text = item.currency
            phoneCodeTextView.text = item.phone_code
            isCheckedImageView.visibility = if (item.isSelected) View.VISIBLE else View.GONE

            when (item.isSelected) {

                true -> {
                    nameTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )
                    currencyTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )
                    phoneCodeTextView.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.myBlue
                        )
                    )

                }

                false -> {

                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is Currency -> 0
            is Filter -> 1
            is Country -> 2
            else -> throw IllegalArgumentException("Invalid data type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return when (viewType) {
            0 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_currecny, parent, false)
                CurrencyViewHolder(itemView) as BaseViewHolder<T>
            }

            1 -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
                FilterViewHolder(itemView) as BaseViewHolder<T>
            }

            2 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_country, parent, false)
                CountryViewHolder(itemView) as BaseViewHolder<T>
            }

            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = dataList[position]
        when (holder) {
            is CurrencyViewHolder -> {
                if (item is Currency) {
                    holder.bind(item)
                }
            }

            is FilterViewHolder -> {
                if (item is Filter) {
                    holder.bind(item)
                }
            }

            is CountryViewHolder -> {
                if (item is Country) {
                    holder.bind(item)
                }
            }

            else -> throw IllegalArgumentException("Invalid ViewHolder type")
        }

        holder.itemView.setOnClickListener {
            // Update the selected property of the clicked item and all other items
            for (i in dataList.indices) {
                when (val currentItem = dataList[i]) {
                    is Currency -> currentItem.isSelected = i == position
                    is Filter -> currentItem.isSelected = i == position
                    is Country -> currentItem.isSelected = i == position
                }
            }

            // Notify the adapter that the data has changed
            notifyDataSetChanged()

            onItemClick(item)
        }

    }}


