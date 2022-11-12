package com.example.imagediary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [ViewDiary.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewDiary : Fragment() {
    private val diaries = mutableListOf<DisplayDiary>()
    private lateinit var diariesRecyclerView: RecyclerView
    private lateinit var diaryAdapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_diary, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        diariesRecyclerView = view.findViewById(R.id.diaries)
        diariesRecyclerView.layoutManager = gridLayoutManager
        diariesRecyclerView.setHasFixedSize(true)
        diaryAdapter = DiaryAdapter(view.context, diaries)
        diariesRecyclerView.adapter = diaryAdapter

        showList();

        // Update the return statement to return the inflated view from above
        return view
    }

    // Display the list of diary's entries
    private fun showList() {
        viewLifecycleOwner.lifecycleScope.launch {
            (activity?.applicationContext as DiaryApplication).db.diaryDao().getAll().collect { databaseList ->
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
    }

    // Add a new diary entry to the database
    private fun addEntry() {
        lifecycleScope.launch(Dispatchers.IO) {
            (activity?.applicationContext as DiaryApplication).db.diaryDao().insert(
                DiaryEntity("Cute dog", "05/03/2020", "description.toString()", "image.org")
            )
        }
    }



    companion object {
        fun newInstance(): ViewDiary {
            return ViewDiary()
        }
    }
}