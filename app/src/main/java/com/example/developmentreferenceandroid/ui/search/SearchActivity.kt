package com.example.developmentreferenceandroid.ui.search

import TopicDetailsDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developmentreferenceandroid.data.GuideTopic
import com.example.developmentreferenceandroid.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels<SearchViewModel>()
    private lateinit var adapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настройка RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchResultAdapter { topic ->
            //TODO: Открыть TopicDetailsDialog с выбранной темой
            showTopicDetailsDialog(topic) // Открываем диалог
        }
        binding.recyclerView.adapter = adapter

        // Подписываемся на изменения текста в поле поиска
        binding.searchEditText.doAfterTextChanged { text ->
            viewModel.search(text.toString(), this)
        }

        // Подписываемся на изменения в LiveData
        viewModel.searchResults.observe(this) { results ->
            adapter.submitList(results)
        }
    }

    private fun showTopicDetailsDialog(topic: GuideTopic) {
        val dialog = TopicDetailsDialog.newInstance(topic.title, topic.content)
        dialog.show(supportFragmentManager, "TopicDetailsDialog")
    }
}