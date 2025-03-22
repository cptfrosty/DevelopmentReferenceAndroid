package com.example.developmentreferenceandroid.data

data class GuideContent(
    val title: String,
    val sections: List<GuideSection>
)

data class GuideSection(
    val title: String,
    val topics: List<GuideTopic>
)
