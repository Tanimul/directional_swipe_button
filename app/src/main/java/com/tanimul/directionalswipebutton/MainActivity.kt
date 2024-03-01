package com.tanimul.directionalswipebutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tanimul.directional_swipe_button.OnSwipeListener
import com.tanimul.directionalswipebutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeBtn.setOnSwipeListener(object : OnSwipeListener {
            override fun onSwipeCompleted() {
                Toast.makeText(this@MainActivity, "onSwipeCompleted", Toast.LENGTH_LONG).show()
            }
        })

    }
}