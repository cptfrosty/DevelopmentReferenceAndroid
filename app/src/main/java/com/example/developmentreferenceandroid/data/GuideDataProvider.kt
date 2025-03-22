import android.content.Context
import com.example.developmentreferenceandroid.R
import com.example.developmentreferenceandroid.data.GuideContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object GuideDataProvider {
    fun loadGuideContent(context: Context): List<GuideContent> {
        val inputStream = context.resources.openRawResource(R.raw.guide_data)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()

        val typeToken = object : TypeToken<List<GuideContent>>() {}.type
        return gson.fromJson(reader, typeToken) ?: emptyList()
    }
}
