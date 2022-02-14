package com.minimalstudios.digitstringmemory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // TMP HERE
    private val levelInit: Int = 5
    private var level: Int = levelInit
    private var digitString: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // TMP: ALL BELOW HERE
    private fun getRandomDigit(): Int {
        return (0..9).random()
    }

    private fun generateDigitString() {
        repeat(level) {
            digitString.add(getRandomDigit())
        }
    }

    fun increaseLevel() {
        level += 1
    }

    fun decreaseLevel() {
        level -= 1
    }
}