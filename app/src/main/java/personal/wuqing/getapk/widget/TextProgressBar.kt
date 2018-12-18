package personal.wuqing.getapk.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

class TextProgressBar(context: Context) : RelativeLayout(context) {
    private val textView = TextView(context)
    private val progressBar = ProgressBar(context)

    init {
        textView.setBackgroundColor(Color.argb(0, 255, 255, 255))
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(textView, params)
        addView(progressBar, params)
    }

    fun setText(resId: Int) = textView.setText(resId)
    fun setTextColor(color: Int) = textView.setTextColor(color)
    var max = progressBar.max
        set(value) {
            field = value
            progressBar.max = value
        }
    var progress = progressBar.progress
        set(value) {
            field = value
            progressBar.progress = value
        }
    var progressColor = progressBar.solidColor
        set(value) {
            field = value
            progressBar.solidColor = value
        }
}
