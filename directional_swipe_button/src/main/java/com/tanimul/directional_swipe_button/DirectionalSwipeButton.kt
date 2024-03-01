package com.tanimul.directional_swipe_button

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import com.tanimul.directional_swipe_button.Animations.animateFadeHide
import com.tanimul.directional_swipe_button.Animations.animateFadeShow
import com.tanimul.directional_swipe_button.databinding.LayoutDirectionalSwipeBinding
import com.tanimul.directional_swipe_button.extentions.convertDpToPx

class DirectionalSwipeButton : ConstraintLayout {

    private lateinit var binding: LayoutDirectionalSwipeBinding
    private var swipeListener: OnSwipeListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        binding = LayoutDirectionalSwipeBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setupTouchListener()
        startLightAnimation()
    }

    // Setter method for the swipe listener
    fun setOnSwipeListener(listener: OnSwipeListener) {
        swipeListener = listener
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchListener() {
        setOnTouchListener(OnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> return@OnTouchListener true

                MotionEvent.ACTION_MOVE -> {
                    // Movement logic here
                    if (event.x > binding.ivSlide.width / 2 && event.x + binding.ivSlide.width / 2 < width && (event.x < binding.ivSlide.x + binding.ivSlide.width || binding.ivSlide.x != 0f)) {
                        // snaps the hint to user touch, only if the touch is within hint width or if it has already been displaced
                        binding.ivSlide.x = event.x - binding.ivSlide.width / 2
                    }
                    if (binding.ivSlide.x + binding.ivSlide.width > width && binding.ivSlide.x + binding.ivSlide.width / 2 < width) {
                        // allows the hint to go up to a max of btn container width
                        binding.ivSlide.x = (width - binding.ivSlide.width).toFloat()
                    }
                    if (event.x < binding.ivSlide.width / 2 && binding.ivSlide.x > 0) {
                        // allows the hint to go up to a min of btn container starting
                        binding.ivSlide.x = 0f
                    }
                    return@OnTouchListener true
                }

                MotionEvent.ACTION_UP -> {
                    //Release logic here
                    when {
                        binding.ivSlide.x + binding.ivSlide.width > width * 0.8f -> {
                            // Swipe completed, Great!
                            successfulSwipe()
                        }

                        else -> {
                            // Swipe not completed, animate back
                            animateBack()
                        }
                    }
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun startLightAnimation() {
        val textView = binding.tvSlide
        val animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1000 // Set animation duration
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { valueAnimator ->
                // Update the position of the light by adjusting its layout parameters
                val fraction = valueAnimator.animatedFraction
                val parentWidth = textView.width
                val startX = parentWidth * 0f
                val endX = parentWidth * 0.95f
                val newX = startX + (endX - startX) * fraction
                val layoutParams = binding.viewMoving.layoutParams as LayoutParams
                layoutParams.marginStart = newX.toInt()
                binding.viewMoving.layoutParams = layoutParams
            }
        }
        animator.start() // Start the animator

    }

    private fun animateBack() {
        ValueAnimator.ofFloat(binding.ivSlide.x, 4.convertDpToPx().toFloat()).apply {
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { binding.ivSlide.x = it.animatedValue as Float }
            duration = 200
            start()
        }
    }

    private fun successfulSwipe() {
        swipeListener?.onSwipeCompleted()

        animateFadeHide(context, binding.layoutSlide)
        animateFadeShow(context, binding.layoutMarked)

        /*        binding.apply {
                    layoutSlide.visibility = View.GONE
                    layoutMarked.visibility = View.VISIBLE
                }*/
    }

    fun setTextSize(@Dimension textSize: Int) {
        binding.apply {
            tvSlide.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            tvMarked.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        }
    }

    fun setSlideTextLabel(slideText: CharSequence) {
        binding.tvSlide.text = slideText
    }

    fun setMarkedTextLabel(markedText: CharSequence) {
        binding.tvMarked.text = markedText
    }

}