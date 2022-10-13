package com.guruthedev.instagram.extensions

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View

fun String.getSpanValues(
    nonClickableText: String,
    clickableText: String,
    spannableColor: Int = Color.BLUE,
    underLineSpannableText: Boolean = false,
    onSpanClick: () -> Unit

): SpannableString {
    val completeText = nonClickableText.plus(clickableText)
    val clickableTextStartPosition = completeText.indexOf(clickableText)
    val clickableTextEndPosition = clickableTextStartPosition + clickableText.length

    val spannableString = SpannableString(this)
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onSpanClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = spannableColor
            ds.isUnderlineText = underLineSpannableText
        }
    }
    spannableString.setSpan(
        clickableSpan,
        clickableTextStartPosition,
        clickableTextEndPosition,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        clickableTextStartPosition,
        clickableTextEndPosition,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}
