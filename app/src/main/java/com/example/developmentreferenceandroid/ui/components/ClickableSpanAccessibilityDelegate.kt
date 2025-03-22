package com.example.developmentreferenceandroid.ui.components

import android.text.SpannableString
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

class ClickableSpanAccessibilityDelegate : AccessibilityDelegateCompat() {

    override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        // Поддержка ссылок
        info.className = android.widget.TextView::class.java.name
        info.isTextSelectable = true
    }

    override fun onPopulateAccessibilityEvent(host: View, event: AccessibilityEvent) {
        super.onPopulateAccessibilityEvent(host, event)

        if (event.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED && host is TextView) {
            val text = host.text
            if (text is SpannableString) {
                event.text.add(text)
            }
        }
    }
}