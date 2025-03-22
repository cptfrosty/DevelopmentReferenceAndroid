package com.example.developmentreferenceandroid.ui.main
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.developmentreferenceandroid.R

data class GuideContent(val id: Int, val title: String, val description: String)

class GuideContentAdapter(private val onItemClicked: (GuideContent) -> Unit) :
    ListAdapter<GuideContent, GuideContentAdapter.GuideContentViewHolder>(GuideContentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_guide_content, parent, false)
        return GuideContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuideContentViewHolder, position: Int) {
        val guideContent = getItem(position)
        holder.bind(guideContent)
        holder.itemView.setOnClickListener {
            onItemClicked(guideContent)
        }
    }

    class GuideContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind(guideContent: GuideContent) {
            titleTextView.text = guideContent.title
        }
    }

    companion object {
        private val GuideContentDiffCallback = object : DiffUtil.ItemCallback<GuideContent>() {
            override fun areItemsTheSame(oldItem: GuideContent, newItem: GuideContent): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GuideContent, newItem: GuideContent): Boolean {
                return oldItem == newItem
            }
        }
    }
}