package com.minimalstudios.digitstringmemory

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityDisplayDigitStringBinding

class DisplayDigitStringActivity : AppCompatActivity() {

    companion object {
        const val DIGIT_STRING = "digit_string"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDisplayDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val digitString = intent?.extras?.get(DIGIT_STRING)

        // TODO: blink between digit to avoid confusion when repeated consecutive
        if (digitString is String) {
            object : CountDownTimer((digitString.length * 1000).toLong(), 1000) {
                var index = 0
                override fun onTick(millisUntilFinished: Long) {
                    binding.tvDisplayDigit.text = "${digitString[index]}"
                    index++
                }

                override fun onFinish() {
                    binding.tvDisplayDigit.text = "done!"
                    // TODO
                }
            }.start()
        }
    }
}