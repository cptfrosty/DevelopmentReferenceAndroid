package com.example.developmentreferenceandroid.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developmentreferenceandroid.data.GuideTopic
import com.example.developmentreferenceandroid.databinding.ActivityDetailBinding
import com.example.developmentreferenceandroid.utils.CLASS_METHOD_MAP
import showCustomAlertDialog

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private lateinit var adapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionTitle = intent.getStringExtra("SECTION_TITLE") ?: "Раздел"

        binding.sectionTitleTextView.text = sectionTitle

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TopicAdapter { topic ->
            // Обработка клика по теме
            Log.d("DetailActivity", "Clicked on topic: ${topic.title}")
            displayTopic(topic) // Отображаем тему при клике на элемент списка
        }
        binding.recyclerView.adapter = adapter

        viewModel.loadTopicsForSection(sectionTitle, this)

        viewModel.topics.observe(this) { topics ->
            adapter.submitList(topics)
        }
    }

    private fun displayTopic(topic: GuideTopic) {
        val classMethodMap = CLASS_METHOD_MAP

        //binding.topicTitleTextView.text = topic.title
        //binding.topicContentView.setTextWithSpans(topic.content, classMethodMap)

        // Пример использования кастомного AlertDialog
        showCustomAlertDialog(
            this,
            "Описание " + topic.title,
            topic.content,
            classMethodMap
        )
    }
}