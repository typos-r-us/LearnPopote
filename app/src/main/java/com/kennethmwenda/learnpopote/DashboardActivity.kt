package com.kennethmwenda.learnpopote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.kennethmwenda.learnpopote.dynamicRVinterface.LoadMore
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*
import kotlin.collections.ArrayList


class DashboardActivity : AppCompatActivity(){
    // Boolean variable to track the state of drawerButton
    var isOpen = false

    // References to the database
    lateinit var databaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val fabDrawerBtn:FloatingActionButton = findViewById(R.id.fab_drawerButton)
        val fabHomeBtn: FloatingActionButton = findViewById(R.id.fab_homeButton)
        val fabCourseBtn: FloatingActionButton = findViewById(R.id.fab_coursesButton)
        val fabProfileBtn: FloatingActionButton = findViewById(R.id.fab_profileButton)
        val fabLogoutBtn: FloatingActionButton = findViewById(R.id.fab_logoutButton)

        val fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open_animation)
        val fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close_animation)
        val fabRotateC = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise)
        val fabRotateCC = AnimationUtils.loadAnimation(this,R.anim.rotate_counter_clockwise)

        fabDrawerBtn.setOnClickListener(){
            if(isOpen){
                fabHomeBtn.startAnimation(fabClose)
                fabCourseBtn.startAnimation(fabClose)
                fabProfileBtn.startAnimation(fabClose)
                fabLogoutBtn.startAnimation(fabClose)
                fabDrawerBtn.startAnimation(fabRotateC)

                isOpen = false
            }else{
                fabHomeBtn.startAnimation(fabOpen)
                fabCourseBtn.startAnimation(fabOpen)
                fabProfileBtn.startAnimation(fabOpen)
                fabLogoutBtn.startAnimation(fabOpen)
                fabDrawerBtn.startAnimation(fabRotateCC)

                fabHomeBtn.isClickable
                fabCourseBtn.isClickable
                fabProfileBtn.isClickable
                fabLogoutBtn.isClickable

                isOpen=true
            }
            fabHomeBtn.setOnClickListener(){
                Toast.makeText(this,"Clicked on Home",Toast.LENGTH_LONG).show()
            }
            fabCourseBtn.setOnClickListener(){
                Toast.makeText(this,"Clicked on Courses",Toast.LENGTH_LONG).show()
            }
            fabProfileBtn.setOnClickListener(){
                Toast.makeText(this,"Clicked on Profile",Toast.LENGTH_LONG).show()
            }
            fabLogoutBtn.setOnClickListener(){
                Toast.makeText(this,"Clicked on Logout",Toast.LENGTH_LONG).show()
            }
        }

        // Retrieve details from registration activity
        val userID = intent.getStringExtra("userId")
        val emailId = intent.getStringExtra("emailId")
        val phoneNo = intent.getStringExtra("phoneNo")
        val userName = intent.getStringExtra("userName")

        tv_userName.text = userName.toString()

        // Dummy data for static rv
        val itemsArrayList:ArrayList<StaticRecyclerViewModel> = ArrayList()
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"C-Sharp"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Dart"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Flutter"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"PHP"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"JavaScript"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"HTML5"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Kotlin"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Java"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Git"))
        itemsArrayList.add(StaticRecyclerViewModel(R.drawable.csharpprogramming,"Databases"))

        val recyclerViewId:RecyclerView = findViewById(R.id.rv_topRv)
        val staticRecyclerViewAdapter:StaticRecyclerViewAdapter = StaticRecyclerViewAdapter(itemsArrayList)
        recyclerViewId.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewId.adapter = staticRecyclerViewAdapter

        // Dummy data for dynamic rv
        val dynamicArrayList:ArrayList<DynamicRecyclerViewModel?> = ArrayList()
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))
        dynamicArrayList.add(DynamicRecyclerViewModel("C-Sharp Programming Course"))

        val dynamicRecyclerView:RecyclerView = findViewById(R.id.rv_bottomRv)
        dynamicRecyclerView.layoutManager=LinearLayoutManager(this) // sets to vertical by default
        val dynamicRecyclerViewAdapter:DynamicRecyclerViewAdapter = DynamicRecyclerViewAdapter(dynamicRecyclerView,this,dynamicArrayList)
        dynamicRecyclerView.adapter = dynamicRecyclerViewAdapter

        dynamicRecyclerViewAdapter.setLoadMore(object : LoadMore {
            override fun onLoadMore() {
                if (dynamicArrayList.size <=10) {
                    dynamicArrayList.add(null)
                    dynamicRecyclerViewAdapter.notifyItemInserted(dynamicArrayList.size-1)
                    Handler().postDelayed(Runnable(){
                        // @Override
                        run(){
                            dynamicArrayList.removeAt(dynamicArrayList.size - 1)
                            dynamicRecyclerViewAdapter.notifyItemRemoved(dynamicArrayList.size-1)

                            val index:Int = dynamicArrayList.size
                            val end:Int = index+10
                            for (i:Int in index .. end){
                                val name:String = UUID.randomUUID().toString()
                                val item:DynamicRecyclerViewModel = DynamicRecyclerViewModel(name)
                                dynamicArrayList.add(item)
                            }
                            dynamicRecyclerViewAdapter.notifyDataSetChanged()
                            dynamicRecyclerViewAdapter.setLoaded()
                        }
                    }, 2000)
                } else
                    Toast.makeText(this@DashboardActivity, "Items loading complete", Toast.LENGTH_SHORT).show()
            }
        })
    }
}