package com.minimalstudios.digitstringmemory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // TMP HERE
    private val levelInit: Int = 5
    private var level: Int = levelInit
    private var digitString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLevel.text = "Level: $level"

        binding.btnRunLevel.setOnClickListener {
            generateDigitString() // TODO: TMP
            displayDigitString()
        }
    }

    private fun displayDigitString() {
        val context = binding.btnRunLevel.context
        val intent = Intent(context, DisplayDigitStringActivity::class.java)
        intent.putExtra(DisplayDigitStringActivity.DIGIT_STRING, digitString)
        context.startActivity(intent)
    }

    // TMP: ALL BELOW HERE
    private fun getRandomDigit(): String {
        return (0..9).random().toString()
    }

    private fun generateDigitString() {
        digitString = ""
        repeat(level) {
            digitString += getRandomDigit()
        }
    }

    fun increaseLevel() {
        level += 1
    }

    fun decreaseLevel() {
        level -= 1
    }
}