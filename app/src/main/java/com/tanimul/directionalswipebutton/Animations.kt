package com.tanimul.directionalswipebutton

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

object Animations {
    fun animateFadeHide(context: Context?, view: View?) {
        if (view != null && view.visibility == View.VISIBLE) {
            val animFadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
            view.startAnimation(animFadeOut)
            view.visibility = View.GONE
        }
    }

    fun animateFadeShow(context: Context?, view: View) {
        if (view.visibility != View.VISIBLE) {
            val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
            view.startAnimation(animFadeIn)
            view.visibility = View.VISIBLE
        }
    }

}