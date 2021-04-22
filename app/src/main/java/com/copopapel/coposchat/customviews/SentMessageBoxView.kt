package com.copopapel.coposchat.customviews

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout

class SentMessageBoxView: MessageBoxView {

    constructor(context:Context): this(context,"")

    constructor(context: Context, text:String): super(context, text) {
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(60,10,5,10)
        lp.gravity = Gravity.END

        this.layoutParams = lp
    }

}