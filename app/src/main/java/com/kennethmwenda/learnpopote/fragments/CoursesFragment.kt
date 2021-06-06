package com.kennethmwenda.learnpopote.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.kennethmwenda.learnpopote.R

class CoursesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_courses, container, false)
        val spinner: Spinner = view.findViewById(R.id.sp_courseTopicName)
        //Create An Array containing topic names
        val topics = arrayOf(
            "Introduction", "Review of HTML Elements", "Inserting Spaces and Line Breaks",
            "What is an HTML Table?", "Creating a Hyperlink","Graphic File Formats" )
        //Set Array in Adapter
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, topics)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    requireContext(),
                    "You have Selected " + topics[p2],
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return view
    }
}