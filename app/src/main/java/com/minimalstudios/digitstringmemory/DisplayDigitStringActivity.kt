package com.minimalstudios.digitstringmemory

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityDisplayDigitStringBinding

class DisplayDigitStringActivity : AppCompatActivity() {

    companion object {
        const val DIGIT_STRING = "digit_string"
    }

    private val intervalBetweenDigits: Long = 1000
    private val intervalTick: Long = 100
    private val ticksBetweenDigits = (intervalBetweenDigits / intervalTick).toInt()
    private val blinkingTicks = 2
    private val tickStartBlink = ticksBetweenDigits - blinkingTicks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDisplayDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val digitString = intent?.extras?.get(DIGIT_STRING)

        if (digitString is String) {
            object : CountDownTimer((digitString.length * intervalBetweenDigits), intervalTick) {
                var tickCounter = 0
                var index = 0

                override fun onTick(millisUntilFinished: Long) {
                    tickCounter++

                    when (tickCounter) {
                        1 -> {
                            binding.tvDisplayDigit.text = "${digitString[index]}"
                            index++
                        }
                        tickStartBlink -> binding.tvDisplayDigit.text = ""
                        ticksBetweenDigits -> tickCounter = 0
                    }
                }

                override fun onFinish() {
                    binding.tvDisplayDigit.text = "done!"
                    // TODO
                }
            }.start()
        }
    }
}