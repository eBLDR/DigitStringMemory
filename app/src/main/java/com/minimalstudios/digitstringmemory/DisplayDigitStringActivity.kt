package com.minimalstudios.digitstringmemory

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityDisplayDigitStringBinding

class DisplayDigitStringActivity : AppCompatActivity() {

    companion object {
        const val DIGIT_STRING = "digit_string"
    }

    private val intervalTick: Long = 100
    private var preparationTicksLeft = 10
    private val intervalBetweenDigits: Long = 1000
    private val ticksBetweenDigits = (intervalBetweenDigits / intervalTick).toInt()
    private val blinkingTicks = 2
    private val tickStartBlink = ticksBetweenDigits - blinkingTicks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDisplayDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val digitString = intent?.extras?.getString(DIGIT_STRING, null)

        if (digitString != null) {
            object : CountDownTimer(
                (digitString.length * intervalBetweenDigits + preparationTicksLeft * intervalTick),
                intervalTick
            ) {
                var tickCounter = 0
                var index = 0

                override fun onTick(millisUntilFinished: Long) {
                    // Preparation time before flashing first digit
                    if (preparationTicksLeft > 0) {
                        preparationTicksLeft--
                        return
                    }

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
                    setResult(RESULT_OK)
                    finish()
                }
            }.start()
        }
    }
}