package com.minimalstudios.digitstringmemory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minimalstudios.digitstringmemory.databinding.ActivityDisplayDigitStringBinding

class DisplayDigitStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDisplayDigitStringBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}