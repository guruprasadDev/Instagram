package com.guruthedev.instagram.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun addReadMore(text: String, textView: TextView) {
    val textStarts = 0
    val spannableString =
        SpannableString(text.substring(textStarts, getWordCount(text)) + "...read more")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadLess(text, textView)
        }

        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = false
            drawState.color = Color.BLACK
        }
    }
    spannableString.setSpan(
        clickableSpan,
        spannableString.length - 10,
        spannableString.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = spannableString
    textView.movementMethod = LinkMovementMethod.getInstance()
}

fun getWordCount(str: String): Int {
    return str.split("\\s+".toRegex()).size
}

fun addReadLess(text: String, textView: TextView) {
    val spannableString = SpannableString("$text read less")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadMore(text, textView)
        }

        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = false
            drawState.color = Color.BLACK
        }
    }
    spannableString.setSpan(
        clickableSpan,
        spannableString.length - 10,
        spannableString.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    textView.text = spannableString
    textView.movementMethod = LinkMovementMethod.getInstance()
}

