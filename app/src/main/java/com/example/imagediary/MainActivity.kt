package com.example.imagediary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

//        diariesRecyclerView.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            diariesRecyclerView.addItemDecoration(dividerItemDecoration)
//        }

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // Attach the layout manager to the recycler view
        diariesRecyclerView.layoutManager = gridLayoutManager

        // Add one item to the database
        lifecycleScope.launch(Dispatchers.IO) {
            (application as DiaryApplication).db.diaryDao().insert(
                DiaryEntity("Walking", "May 20,2002", "I like walking around", "image.org")
            )
        }


    }
}