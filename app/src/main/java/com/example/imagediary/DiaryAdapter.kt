package com.example.imagediary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val DIARY_EXTRA = "DIARY_EXTRA"
private const val TAG = "DiaryAdapter"

class DiaryAdapter(private val context: Context, private val diaries: List<DisplayDiary>) :
    RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_diary, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual diary and bind to holder
        val diary = diaries[position]
        holder.bind(diary)
    }

    override fun getItemCount() = diaries.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val titleTextView = itemView.findViewById<TextView>(R.id.titleView)
        private val dateTextView = itemView.findViewById<TextView>(R.id.dateView)
        private val photoImageView = itemView.findViewById<ImageView>(R.id.photoView)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(diary: DisplayDiary) {
            titleTextView.text = diary.title
            dateTextView.text = diary.date
        }

        // TODO: FIX THIS FOR DETAIL ACTIVITY
        override fun onClick(v: View?) {
//            // TODO: Get selected diary
//            val diary = diaries[absoluteAdapterPosition]
//
//            // TODO: Navigate to Details screen and pass selected diary
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra(DIARY_EXTRA, diary)
//            context.startActivity(intent)
        }
    }
}