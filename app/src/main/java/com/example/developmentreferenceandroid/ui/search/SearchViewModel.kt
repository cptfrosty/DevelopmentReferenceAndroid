package com.example.developmentreferenceandroid.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.developmentreferenceandroid.data.GuideContent
import com.example.developmentreferenceandroid.data.GuideTopic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableLiveData<List<GuideTopic>>()
    val searchResults: LiveData<List<GuideTopic>> = _searchResults

    fun search(query: String, context: Context) {
        val allTopics = getAllTopics(context)
        val results = allTopics.filter { topic ->
            topic.title.contains(query, ignoreCase = true) ||
                    topic.content.contains(query, ignoreCase = true)
        }
        _searchResults.value = results
    }

    private fun getAllTopics(context: Context): List<GuideTopic> {
        try {
            val jsonString = context.assets.open("guide_content.json")
                .bufferedReader()
                .use { it.readText() }

            val gson = Gson()
            val listGuideContentType = object : TypeToken<List<GuideContent>>() {}.type
            val guideContentList: List<GuideContent> = gson.fromJson(jsonString, listGuideContentType)

            return guideContentList.flatMap { guideContent ->
                guideContent.sections.flatMap { section ->
                    section.topics
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}