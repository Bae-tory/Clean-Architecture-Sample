package com.sungjae.portfolio.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView : AppCompatImageView {

    private val clipPath = Path()
    private var radius = 30.0f.dptoPx()
    private val rect by lazy {
        RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun rectRadius(radius: Float) {
        this.radius = radius
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    private fun Float.dptoPx(): Float {
        return this * resources.displayMetrics.density
    }
}