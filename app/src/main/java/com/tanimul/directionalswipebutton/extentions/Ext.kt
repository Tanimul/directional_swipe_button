package com.tanimul.directionalswipebutton.extentions

import android.content.res.Resources

fun Int.convertDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()