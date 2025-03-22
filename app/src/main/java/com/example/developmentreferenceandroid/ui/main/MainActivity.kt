package com.example.developmentreferenceandroid.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developmentreferenceandroid.databinding.ActivityMainBinding
import com.example.developmentreferenceandroid.ui.detail.DetailActivity
import com.example.developmentreferenceandroid.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: GuideContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = GuideContentAdapter { section ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("SECTION_TITLE", section.title)
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter

        viewModel.guideContent.observe(this) { content ->
            val guideContentList: MutableList<GuideContent> = mutableListOf()

            content.forEach { dataGuideContent ->
                dataGuideContent.sections.forEach { dataGuideSection -> // Проходимся по всем секциям в dataGuideContent
                    // Создаем ui.main.GuideContent для каждой секции
                    val uiGuideContent = GuideContent(
                        id = dataGuideSection.hashCode(), // Уникальный ID для секции
                        title = dataGuideSection.title, // Заголовок секции
                        description = "" // Описание можно оставить пустым
                    )
                    guideContentList.add(uiGuideContent)
                }
            }
            adapter.submitList(guideContentList)
        }

        binding.searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        viewModel.loadGuideContent(this)
    }
}