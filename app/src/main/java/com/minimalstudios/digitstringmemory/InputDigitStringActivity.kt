package com.minimalstudios.digitstringmemory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityInputDigitStringBinding

class InputDigitStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInputDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}