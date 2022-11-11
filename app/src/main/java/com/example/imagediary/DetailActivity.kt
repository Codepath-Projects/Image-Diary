package com.example.imagediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.Dispatchers

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val submit = findViewById<Button>(R.id.submit_button)

        submit.setOnClickListener() {
            var date_entered = findViewById<View>(R.id.date_entry) as EditText
            var title_entered = findViewById<View>(R.id.title_entry) as EditText
            var description_entered = findViewById<View>(R.id.description_entry) as EditText

            // Check if the title and date are not empty. If it's empty, tell the user to write inputs for it.
            // Otherwise, send the data back to the main activity
            if (title_entered.getText().toString() == "") {
                Toast.makeText(this, "Invalid: Enter a title", Toast.LENGTH_LONG).show()
            } else if (date_entered.getText().toString() == "") {
                Toast.makeText(this, "Invalid: Enter a date", Toast.LENGTH_LONG).show()
            } else {
                // Prepare data intent
                val data = Intent()

                // Pass relevant data back as a result
                data.putExtra("title", title_entered.getText().toString());
                data.putExtra("description", description_entered.getText().toString())
                data.putExtra("date", date_entered.getText().toString());

                // Activity finished ok, return the data
                setResult(RESULT_OK, data) // set result code and bundle data for response
                finish() // closes the activity, pass data to parent
            }

        }

    }
}