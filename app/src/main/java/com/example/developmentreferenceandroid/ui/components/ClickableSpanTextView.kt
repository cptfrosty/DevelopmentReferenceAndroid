package com.example.developmentreferenceandroid.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat

class ClickableSpanTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        movementMethod = LinkMovementMethod.getInstance()
        ViewCompat.setAccessibilityDelegate(this, ClickableSpanAccessibilityDelegate()) // Устанавливаем AccessibilityDelegate
    }

    fun setTextWithSpans(text: String, classMethodMap: Map<String, String>) {
        val spannableString = SpannableString(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
        var startIndex = 0

        for ((keyword, url) in classMethodMap) {
            while (startIndex < spannableString.length) {
                val index = spannableString.toString().indexOf(keyword, startIndex)
                if (index == -1) {
                    break
                }
                val endIndex = index + keyword.length

                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Log.d("ClickableSpanTextView", "Clicked on $keyword, URL: $url")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                }
                spannableString.setSpan(
                    clickableSpan,
                    index,
                    endIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startIndex = endIndex
            }
            startIndex = 0 // Сбрасываем startIndex для следующего keyword
        }

        setText(spannableString)
        movementMethod = LinkMovementMethod.getInstance()
    }
}