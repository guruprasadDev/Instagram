package com.guruthedev.instagram.utils

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView


fun addReadMore(text: String, textView: TextView, context: Context) {
    val textStarts = 0
    val textLength = text.length
    val spannableString = SpannableString(text.substring(textStarts, textLength) + "...read more")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadLess(text, textView, context)
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

fun addReadLess(text: String, textView: TextView, context: Context) {
    val spannableString = SpannableString("$text read less")
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(view: View) {
            addReadMore(text, textView, context)
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

