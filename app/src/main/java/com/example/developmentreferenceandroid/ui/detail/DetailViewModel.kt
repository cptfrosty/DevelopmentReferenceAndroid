package com.example.developmentreferenceandroid.ui.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.developmentreferenceandroid.data.GuideContent
import com.example.developmentreferenceandroid.data.GuideTopic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel : ViewModel() {

    private val _topics = MutableLiveData<List<GuideTopic>>()
    val topics: LiveData<List<GuideTopic>> = _topics

    fun loadTopicsForSection(sectionTitle: String, context: Context) {
        try {
            val jsonString = context.assets.open("guide_content.json")
                .bufferedReader()
                .use { it.readText() }

            val gson = Gson()
            val listGuideContentType = object : TypeToken<List<GuideContent>>() {}.type
            val guideContentList: List<GuideContent> = gson.fromJson(jsonString, listGuideContentType)

            val topicsForSection = mutableListOf<GuideTopic>()

            for (guideContent in guideContentList) {
                for (section in guideContent.sections) {
                    if (section.title == sectionTitle) {
                        topicsForSection.addAll(section.topics)
                    }
                }
            }

            _topics.value = topicsForSection
            Log.d("DetailViewModel", "Loaded topics for section: $sectionTitle, topic count: ${topicsForSection.size}")
        } catch (e: Exception) {
            Log.e("DetailViewModel", "Error loading topics", e)
            _topics.value = emptyList()
        }
    }
}