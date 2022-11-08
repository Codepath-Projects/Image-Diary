package com.example.imagediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.Dispatchers

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val submit = findViewById<Button>(R.id.submit_button)
        submit.setOnClickListener() {

            var date_entered = findViewById<View>(R.id.date_entry) as EditText
            var title_entered = findViewById<View>(R.id.title_entry) as EditText
            var description_entered = findViewById<View>(R.id.description_entry) as EditText

            // Prepare data intent
            var send = Diary_Entry(date_entered.toString(), title_entered.toString(), description_entered.toString())

            val data = Intent()
            // Pass relevant data back as a result
            data.putExtra("data_passed", send )
            // Activity finished ok, return the data
            setResult(RESULT_OK, data) // set result code and bundle data for response
            finish() // closes the activity, pass data to parent


        }



    }
}