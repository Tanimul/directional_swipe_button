package bd.com.media365.directional_swipe_button.extentions

import android.content.res.Resources

fun Int.convertDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()