package com.example.developmentreferenceandroid.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.developmentreferenceandroid.data.GuideContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel : ViewModel() {

    private val _guideContent = MutableLiveData<List<GuideContent>>()
    val guideContent: LiveData<List<GuideContent>> = _guideContent

    fun loadGuideContent(context: Context) {
        try {
            val jsonString = context.assets.open("guide_content.json")
                .bufferedReader()
                .use { it.readText() }

            val gson = Gson()
            val listGuideContentType = object : TypeToken<List<GuideContent>>() {}.type
            val guideContentList: List<GuideContent> = gson.fromJson(jsonString, listGuideContentType)

            _guideContent.value = guideContentList
        } catch (e: Exception) {
            e.printStackTrace()
            _guideContent.value = emptyList()
        }
    }
}