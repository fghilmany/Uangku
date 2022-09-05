package com.fghilmany.uangku.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomFloatingActionButton: FloatingActionButton {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

       if (isEnabled){
           alpha = 1f
       }else{
           alpha - 0.4f
       }
    }
}