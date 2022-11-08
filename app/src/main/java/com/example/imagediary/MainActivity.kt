package com.example.imagediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // If the user comes back to this activity from EditActivity
            // with no error or cancellation
            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data

                // Get the data passed from EditActivity
                if (data != null) {
                    val serialized_data = data.getSerializableExtra("data_passed")
                    Log.i("String", serialized_data.toString())
                    //have to access the serialized data.
                }
            }
        }

        var add_entry_btn = findViewById<Button>(R.id.add_entry)
        add_entry_btn.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            intent.putExtra("data_passed", "CodePath")
            editActivityResultLauncher.launch(intent)
        }
    }
}