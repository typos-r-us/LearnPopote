package com.kennethmwenda.learnpopote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennethmwenda.learnpopote.dynamicRVinterface.LoadMore
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

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