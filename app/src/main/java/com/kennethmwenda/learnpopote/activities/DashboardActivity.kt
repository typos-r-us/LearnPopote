package com.kennethmwenda.learnpopote.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kennethmwenda.learnpopote.R
import com.kennethmwenda.learnpopote.adapters.CourseItemAdapter
import com.kennethmwenda.learnpopote.models.CourseItemModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.ArrayList

class DashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        // Set user name or dashboard welcome text
        val sharedPrefs = getSharedPreferences("userMap",Context.MODE_PRIVATE)
        val _userName: String? = sharedPrefs.getString("fname",", welcome.")
        tv_userName.text=_userName
        // Set list view for Course selection
        val listView = findViewById<ListView>(R.id.lv_coursesList)
        val list = ArrayList<CourseItemModel>() //mutableListOf<CourseItemModel>()
        list.add(CourseItemModel("HTML","Learn HTML. Discover a simplified approach to HTML design.",R.drawable.html))
        list.add(CourseItemModel("JavaScript","Learn JavaScript, the language of the future, taught today.",R.drawable.javascript))
        list.add(CourseItemModel("CSS","Make simple CSS designs and develop remarkable skills.",R.drawable.css))
        list.add(CourseItemModel("Databases","Learn to store and retrieve your app data in efficient and effective database models.",R.drawable.databases))
        list.add(CourseItemModel("Kotlin","Adopt android development in Kotlin, backed by Google.",R.drawable.kotlin))
        list.add(CourseItemModel("Git","Learn to use free, online resources to backup and maintain your code with git and github.",R.drawable.git))

        val arrayAdapter = CourseItemAdapter(this,R.layout.course_item_list,R.id.tv_text2,list)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                val intent = Intent(this,HtmlCourseActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Clicked on HTML", Toast.LENGTH_SHORT).show()
            }
            if (position == 1) {
                Toast.makeText(this, "Clicked on JavaScript", Toast.LENGTH_LONG).show()
            }
            if (position == 2) {
                Toast.makeText(this, "Clicked on CSS", Toast.LENGTH_LONG).show()
            }
            if (position == 3) {
                Toast.makeText(this, "Clicked on Databases", Toast.LENGTH_LONG).show()
            }
            if (position == 4) {
                Toast.makeText(this, "Clicked on Kotlin", Toast.LENGTH_LONG).show()
            }
            if (position == 5) {
                Toast.makeText(this, "Clicked on Git", Toast.LENGTH_LONG).show()
            }
        }
        val etSearch:EditText = findViewById(R.id.et_search)
        etSearch.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                arrayAdapter!!.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable) {}
        })

    }
}