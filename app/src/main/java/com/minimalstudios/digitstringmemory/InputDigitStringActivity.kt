package com.minimalstudios.digitstringmemory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityInputDigitStringBinding

class InputDigitStringActivity : AppCompatActivity() {

    companion object {
        const val INPUT_DIGIT_STRING = "input_digit_string"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInputDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (btn in listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9,
        )) {
            btn.setOnClickListener {
                binding.tvInputDigitString.append(btn.text)
            }
        }

        binding.btnAC.setOnClickListener {
            binding.tvInputDigitString.text = ""
        }

        binding.btnDelete.setOnClickListener {
            val length = binding.tvInputDigitString.length()
            if (length > 0) {
                binding.tvInputDigitString.text =
                    binding.tvInputDigitString.text.subSequence(0, length - 1)
            }
        }

        binding.btnOK.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(INPUT_DIGIT_STRING, binding.tvInputDigitString.text.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}