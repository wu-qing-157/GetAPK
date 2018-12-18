package personal.wuqing.getapk.widget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

class TextProgressBar(context: Context) : RelativeLayout(context) {
    private val textView = TextView(context)
    private val progressBar = ProgressBar(context)

    init {
        textView.setTextColor(Color.BLACK)
        textView.setBackgroundColor(Color.argb(0, 255, 255, 255))
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(textView, params)
        addView(progressBar, params)
    }

    var text = textView.text!!
        set(value) {
            field = value
            textView.text = value
        }
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
    fun setColorFilter(color: Int, mode: PorterDuff.Mode) {
        progressBar.progressDrawable.setColorFilter(color, mode)
    }
}
