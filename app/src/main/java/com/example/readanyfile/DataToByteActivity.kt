package com.example.readanyfile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.readanyfile.databinding.ActivityDataToByteBinding
import java.io.*

class DataToByteActivity: AppCompatActivity() {
    
    private lateinit var binding: ActivityDataToByteBinding
    private val REQ_PICK_FILE = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_to_byte)

        binding.btnSelectFile.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // Any type file can be open but with required app that installed
            intent.type = "*/*"
            intent.flags = Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
            startActivityForResult(intent, REQ_PICK_FILE)
        }
        val content = "Hello ".toByteArray()
        binding.txtVInputOutputStream.text = content.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQ_PICK_FILE &&
            data != null && data.data != null) {
            binding.img.setImageURI(data.data)
            try {

//                val fileName = data.data
//                val file = File(fileName.toString())
//                val inStream: InputStream = file.inputStream()
//                val content = inStream.readBytes()

                //Get any type of data(photo, audio, pdf, e.g.) then convert to Bytes
                val fileName = data.data
                val inputStream = this.contentResolver.openInputStream(fileName!!.normalizeScheme())
                val content = inputStream?.readBytes()

                binding.txtVInputOutputStream.text = content.toString()

                Toast.makeText(this,"Byte data is ready.", Toast.LENGTH_LONG).show()
            }
            catch (e: FileNotFoundException ) {
                Toast.makeText(this,"FileNotFoundException!", Toast.LENGTH_LONG).show()
                e.printStackTrace()}
            catch (e: IOException) {
                Toast.makeText(this,"IOException!", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }
}
}