package com.example.imagediary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener


/**
 * A simple [Fragment] subclass.
 * Use the [NewDiary.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewDiary : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var title_entered: EditText
    private lateinit var date_entered: EditText
    private lateinit var description_entered: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_new_diary, container, false)

        val submit = view.findViewById<Button>(R.id.submit_button)
        val camera = view.findViewById<Button>(R.id.camera_button)

        camera.setOnClickListener() {

        }

        submit.setOnClickListener() {
            date_entered = view.findViewById<View>(R.id.date_entry) as EditText
            title_entered = view.findViewById<View>(R.id.title_entry) as EditText
            description_entered = view.findViewById<View>(R.id.description_entry) as EditText

            // Check if the title and date are not empty. If it's empty, tell the user to write inputs for it.
            // Otherwise, send the data back to the main activity
            if (title_entered.getText().toString() == "") {
                Toast.makeText(getActivity(), "Invalid: Enter a title", Toast.LENGTH_LONG).show()
            } else if (date_entered.getText().toString() == "") {
                Toast.makeText(getActivity(), "Invalid: Enter a date", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(getActivity(), "Yay", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    companion object {
        fun newInstance(): NewDiary {
            return NewDiary()
        }

    }
}