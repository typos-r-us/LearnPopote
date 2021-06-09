package com.kennethmwenda.learnpopote.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.kennethmwenda.learnpopote.R
import com.kennethmwenda.learnpopote.adapters.CourseItemAdapter
import com.kennethmwenda.learnpopote.models.CourseItemModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.ArrayList

class DashboardActivity : AppCompatActivity(){
    var rootRef = FirebaseDatabase.getInstance().reference
    var usersDataRef = rootRef.child("usersData")
    var currentUser = FirebaseAuth.getInstance().currentUser
    var currentUserId = currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        // Try to hide system navbar by setting fullscreen activity.
        hideSystemUI()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // End hide system navbar by setting fullscreen activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        // Set user name or dashboard welcome text from Firebase
        //===== old implementation. did not work :-( =====//
        // val sharedPrefs = getSharedPreferences("userMap",Context.MODE_PRIVATE)
        // val _userName: String? = sharedPrefs.getString("fname",", welcome.")
        //===== end old implementation. did not work :-( =====//
        //===== new implementation. will this work :-( =====//
        if (currentUserId!=null){
            usersDataRef.child(currentUserId.toString()).addListenerForSingleValueEvent( object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    // TODO("Not yet implemented")
                    val currentUserName = snapshot.child("userName").value.toString()
                   // Haya, sasa get the first name using regex and put in the userName tv
                    val fName = currentUserName.split("\\s".toRegex())[0]
                    tv_userName.text=fName
                }
                override fun onCancelled(error: DatabaseError) {
                    // Do nothing: TODO("Not yet implemented")
                }
            })
        }else{
            // do something if the user is null
            tv_userName.text="welcome."
        }
        //===== new implementation. This worked!! YAY :-D =====//
        // tv_userName.text=_userName
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
                val intent = Intent(this,JavaScriptCourseActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Clicked on JavaScript", Toast.LENGTH_SHORT).show()
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
                arrayAdapter.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable) {}
        })
        // Bottom Nav buttons
        val bottomNav : ChipNavigationBar = findViewById(R.id.bottomChip)
        bottomNav.setOnItemSelectedListener { item->
            when(item){
                R.id.mn_logout->{
                    // go to login page, clear all activities, sign out user
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // clears all past activities
                    startActivity(intent)
                    finish()
                    FirebaseAuth.getInstance().signOut()
                }
                R.id.mn_profile->{
                    // Go to user profile page
                    val intent = Intent(this, UserProfile::class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }
    private fun hideSystemUI(){
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        //or View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE
                )
    }
}