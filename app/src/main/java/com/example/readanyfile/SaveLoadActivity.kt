package com.example.readanyfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.readanyfile.databinding.ActivitySaveLoadBinding
import java.io.*
import java.lang.StringBuilder

class SaveLoadActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySaveLoadBinding
    private val FILE_NAME = "example.txt"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save_load)

        binding.btnSave.setOnClickListener {
            val text: String = binding.edtT.text.toString()
            var fos: FileOutputStream? = null

            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
                fos.write(text.toByteArray())

                binding.edtT.setText("")
                Toast.makeText(this, "Save to $filesDir/$FILE_NAME", Toast.LENGTH_LONG).show()

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (fos != null) {
                    try {
                        fos.close()
                        
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        }

        binding.btnLoad.setOnClickListener {
            var fis: FileInputStream? = null

            try {
                fis = openFileInput(FILE_NAME)
                val isr = InputStreamReader(fis)
                val br = BufferedReader(isr)
                val sb = StringBuilder()
                var text: String? = null

                while ((br.readLine().also { text = it }) != null) {
                    sb.append(text)
                    }
                binding.edtT.setText(sb.toString())

                } catch (e: FileNotFoundException) {
                e.printStackTrace()

            } catch (e: IOException) {
                e.printStackTrace()

            } finally {
                if (fis != null) {
                    try {
                        fis.close()

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }
}