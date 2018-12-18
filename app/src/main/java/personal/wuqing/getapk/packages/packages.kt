package personal.wuqing.getapk.packages

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.ProgressBar
import android.widget.ScrollView

val packageList = mutableListOf<PackageInfo>()

fun updatePackages(context: Context, scrollView: ScrollView, textProgressBar: ProgressBar) {
    val packageManager = context.packageManager
    val rawList = packageManager.getInstalledPackages(0)
    progressBar.max = rawList.size
}