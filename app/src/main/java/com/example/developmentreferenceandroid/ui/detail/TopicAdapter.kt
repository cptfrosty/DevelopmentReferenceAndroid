package com.example.developmentreferenceandroid.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentreferenceandroid.R
import com.example.developmentreferenceandroid.data.GuideTopic

class TopicAdapter(private val onItemClicked: (GuideTopic) -> Unit) :
    ListAdapter<GuideTopic, TopicAdapter.TopicViewHolder>(TopicDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_topic, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
        holder.itemView.setOnClickListener {
            onItemClicked(topic)
        }
    }

    class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.topicTitleTextView)

        fun bind(topic: GuideTopic) {
            titleTextView.text = topic.title
            Log.d("TopicAdapter", "Title: ${topic.title}")
        }
    }

    companion object {
        private val TopicDiffCallback = object : DiffUtil.ItemCallback<GuideTopic>() {
            override fun areItemsTheSame(oldItem: GuideTopic, newItem: GuideTopic): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GuideTopic, newItem: GuideTopic): Boolean {
                return oldItem == newItem
            }
        }
    }
}