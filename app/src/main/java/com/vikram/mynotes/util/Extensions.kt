package com.vikram.mynotes.util

import android.os.SystemClock
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vikram.mynotes.util.AppConstants.CLICK_DEBOUNCE_DURATION
import com.vikram.mynotes.util.AppConstants.DD_MM_YY
import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(format: String = DD_MM_YY): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format(calendar.time)
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(
        this.context,
        DividerItemDecoration.VERTICAL
    )
    val drawable = ContextCompat.getDrawable(
        this.context,
        drawableRes
    )
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

/**
 * Debounce click to handle multiple user clicks
 */
fun View.setClickListener(
    debounceTime: Long = CLICK_DEBOUNCE_DURATION,
    action: () -> Unit
) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}