package personal.wuqing.getapk.packages

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import personal.wuqing.getapk.R
import personal.wuqing.getapk.widget.TextProgressBar
import java.text.Collator
import java.util.*
import java.util.function.Function

val packageList = mutableListOf<PackageInfo>()

fun updatePackages(context: Context, radioGroup: RadioGroup, textProgressBar: TextProgressBar) {
    Thread {
        textProgressBar.post {
            textProgressBar.setOnClickListener(null)
            textProgressBar.text = context.getString(R.string.get_package_list)
            textProgressBar.max = 0
            textProgressBar.progress = 0
        }
        val packageManager = context.packageManager
        val rawList = packageManager.getInstalledPackages(0)
        textProgressBar.post {
            textProgressBar.text = context.getString(R.string.sort_package)
            textProgressBar.max = 0
            textProgressBar.progress = 0
        }
        rawList.sortWith(Comparator.comparing(
                Function { it.applicationInfo.loadDescription(packageManager) },
                Collator.getInstance()))
        textProgressBar.post {
            textProgressBar.text = context.getString(R.string.resolve_packages)
            textProgressBar.max = rawList.size
            textProgressBar.progress = 0
            textProgressBar.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN)
        }
        for (info in rawList) {
            val button = RadioButton(context)
            button.text = info.applicationInfo.loadDescription(packageManager)
            button.id = textProgressBar.progress
            val systemApplication =
                    info.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM > 0
            button.setTextColor(if (systemApplication) Color.RED else Color.BLACK)
            button.tag = systemApplication
            radioGroup.addView(button)
            textProgressBar.post { textProgressBar.progress++ }
        }
        textProgressBar.post {
            textProgressBar.text = context.getString(R.string.done)
            radioGroup.visibility = View.VISIBLE
        }
        Thread {
            for (i in 0..17) {
                Thread.sleep(40)
                textProgressBar.post {
                    textProgressBar.setColorFilter(
                            Color.rgb(255 - 15 * i, 15 * i, 255 - 15 * i),
                            PorterDuff.Mode.SRC_IN)
                }
            }
            textProgressBar.post {
                textProgressBar.text = context.getString(R.string.tap_to_refresh_package_list)
                textProgressBar.setOnClickListener {
                    radioGroup.visibility = View.GONE
                    updatePackages(context, radioGroup, textProgressBar)
                }
            }
        }.start()
    }.start()
}
