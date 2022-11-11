package com.example.imagediary

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val diaries = mutableListOf<DisplayDiary>()
    private lateinit var diariesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the adapter with diaries
        diariesRecyclerView = findViewById(R.id.diaries)
        val diaryAdapter = DiaryAdapter(this, diaries)
        diariesRecyclerView.adapter = diaryAdapter

        lifecycleScope.launch {
            (application as DiaryApplication).db.diaryDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayDiary(
                        entity.title,
                        entity.date,
                        entity.description,
                        entity.photo_path
                    )
                }.also { mappedList ->
                    diaries.clear()
                    diaries.addAll(mappedList)
                    diaryAdapter.notifyDataSetChanged()
                }
            }
        }

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // Attach the layout manager to the recycler view, making the list to appear in a grid layout
        diariesRecyclerView.layoutManager = gridLayoutManager

        var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // If the user comes back to this activity from EditActivity
            // with no error or cancellation
            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data

                // Get the data passed from DetailActivity
                if (data != null) {
                    val title = data.extras!!.getString("title");
                    val description = data.extras!!.getString("description")
                    val date = data.extras!!.getString("date")

                    //Test if appear
                    Toast.makeText(this, date.toString(), Toast.LENGTH_LONG).show();

                    // Add the new diary entry to the database
                    lifecycleScope.launch(Dispatchers.IO) {
                        (application as DiaryApplication).db.diaryDao().insert(
                            DiaryEntity(title.toString(), date.toString(), description.toString(), "image.org")
                        )
                    }
                }
            }
        }

        /* TODO: GET RID OF THIS BUTTON WHEN YOU IMPLEMENT THE FRAGMENT TO THIS
        Note that you don't need to pass data back and forth (MainActivity -> DetailActivity and DetailActivity -> MainActivity)
        You just need to pass data from DetailActivity to MainActivity since we have the navigation bar where the user can go directly making a diary entry,
        meaning, you need to modify the intent code in both MainActivity and DetailActivity */
        var add_entry_btn = findViewById<Button>(R.id.add_entry)
        add_entry_btn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data_passed", "CodePath")
            editActivityResultLauncher.launch(intent)
        }

        // TODO: Get rid of this once you are done testing the app
//        lifecycleScope.launch(Dispatchers.IO) {
//            (application as DiaryApplication).db.diaryDao().deleteAll()
//        }

    }
}