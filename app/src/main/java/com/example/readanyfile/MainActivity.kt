package com.example.readanyfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.readanyfile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnFileToByte.setOnClickListener {
            startActivity(Intent(this, DataToByteActivity::class.java))
        }

        binding.btnFileSaveLoad.setOnClickListener {
            startActivity(Intent(this, SaveLoadActivity::class.java))
        }
    }
}