package com.minimalstudios.digitstringmemory

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultDisplayDigitString: ActivityResultLauncher<Intent>
    private lateinit var resultInputDigitString: ActivityResultLauncher<Intent>

    private val levelInit: Int = 5
    private var level: Int = levelInit
    private var digitString: String = ""
    private var inputDigitString: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Register contracts
        resultDisplayDigitString = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                runInputDigitString()
            } else {
                assessResult()
            }
        }

        resultInputDigitString = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                inputDigitString =
                    it.data?.getStringExtra(InputDigitStringActivity.INPUT_DIGIT_STRING)
            }
            assessResult()
        }

        // Prepare main menu
        renderLevel()

        binding.btnRunLevel.setOnClickListener {
            runLevel()
        }
    }

    private fun runLevel() {
        inputDigitString = ""
        generateDigitString()
        runDisplayDigitString()
    }

    private fun generateDigitString() {
        digitString = ""
        repeat(level) {
            digitString += getRandomDigit()
        }
    }

    private fun runDisplayDigitString() {
        val intent = Intent(this, DisplayDigitStringActivity::class.java)
        intent.putExtra(DisplayDigitStringActivity.DIGIT_STRING, digitString)
        resultDisplayDigitString.launch(intent)
    }

    private fun runInputDigitString() {
        val intent = Intent(this, InputDigitStringActivity::class.java)
        resultInputDigitString.launch(intent)
    }

    private fun assessResult() {
        when (inputDigitString) {
            digitString -> levelPassed()
            else -> levelFailed()
        }
        binding.tvHistoric.text = digitString
    }

    private fun getRandomDigit(): String {
        return (0..9).random().toString()
    }

    private fun renderLevel() {
        binding.tvLevel.text = getString(R.string.level, level)
    }

    private fun levelPassed() {
        binding.tvResult.text = getString(R.string.passed)
        level++
        renderLevel()
    }

    private fun levelFailed() {
        binding.tvResult.text = getString(R.string.failed)
        if (level > 1) {
            level--
        }
        renderLevel()
    }
}