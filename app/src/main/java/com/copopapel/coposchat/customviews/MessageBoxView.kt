package com.copopapel.coposchat.customviews

import android.content.Context
import android.graphics.Color
import android.text.TextPaint
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.copopapel.coposchat.R

/**
 * TODO: document your custom view class.
 */
class MessageBoxView : AppCompatTextView {

    private var _exampleString: String? = null // TODO: use a default from R.string...
    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
    private var _exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    private var textPaint: TextPaint? = null
    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    constructor(context: Context): this(context,"") {

    }

    constructor(context: Context, text: String): super(context) {
        this.text = text
        this.setPadding(18)

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(60,10,5,10)
        lp.gravity = Gravity.RIGHT

        this.layoutParams = lp

        this.background = resources.getDrawable(R.color.colorWhite)

        this.gravity = Gravity.RIGHT
    }
}
