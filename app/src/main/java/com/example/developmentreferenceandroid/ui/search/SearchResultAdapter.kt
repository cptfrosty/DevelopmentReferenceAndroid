package com.example.developmentreferenceandroid.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentreferenceandroid.R
import com.example.developmentreferenceandroid.data.GuideTopic

class SearchResultAdapter(private val onItemClicked: (GuideTopic) -> Unit) :
    ListAdapter<GuideTopic, SearchResultAdapter.SearchResultViewHolder>(SearchResultDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val searchResult = getItem(position)
        holder.bind(searchResult)
        holder.itemView.setOnClickListener {
            onItemClicked(searchResult)
        }
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.searchResultTitleTextView)

        fun bind(topic: GuideTopic) {
            titleTextView.text = topic.title
        }
    }

    companion object {
        private val SearchResultDiffCallback = object : DiffUtil.ItemCallback<GuideTopic>() {
            override fun areItemsTheSame(oldItem: GuideTopic, newItem: GuideTopic): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GuideTopic, newItem: GuideTopic): Boolean {
                return oldItem == newItem
            }
        }
    }
}