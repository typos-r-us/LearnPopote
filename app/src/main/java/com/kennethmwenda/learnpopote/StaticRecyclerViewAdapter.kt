package com.kennethmwenda.learnpopote

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.kennethmwenda.learnpopote.StaticRecyclerViewAdapter.StaticRecycleViewHolder
import com.kennethmwenda.learnpopote.dynamicRVinterface.UpdateRecyclerView

class StaticRecyclerViewAdapter : RecyclerView.Adapter<StaticRecycleViewHolder> {
    val listItems: ArrayList<StaticRecyclerViewModel>
    var updateRecyclerView:UpdateRecyclerView
    var activity:Activity
    var check = true
    var select = true

    constructor(listItems: ArrayList<StaticRecyclerViewModel>, activity:Activity, updateRecyclerView:UpdateRecyclerView) : super() {
        this.listItems = listItems
        this.activity = activity
        this.updateRecyclerView = updateRecyclerView
    }

    var rowIndex: Int = -1


    class StaticRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView:ImageView = itemView.findViewById(R.id.iv_rvImg)
        val textView:TextView = itemView.findViewById(R.id.tv_rvTxt)
        val linearLayout:LinearLayout = itemView.findViewById(R.id.ll_staticRv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaticRecycleViewHolder {
        //  TODO("Not yet implemented")
        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.static_recycler_view_items,parent,false)
        return StaticRecycleViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaticRecycleViewHolder, position: Int) {
      //  TODO("Not yet implemented")
        // val currentItem:StaticRecyclerViewModel = itemCount.get(position)
        val currentItem:StaticRecyclerViewModel = listItems[position] // listItems.get(position)
        holder.imageView.setImageDrawable(currentItem.getImage().toDrawable())
        holder.textView.text = currentItem.getText()

        if(check){
            val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
            dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Intro", R.drawable.dartlang))
            dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, UI Dev", R.drawable.dartlang))
            dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Maps", R.drawable.dartlang))
            dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Firebase", R.drawable.dartlang))

            updateRecyclerView.callback(position,dynamicRVItemsList)
            check=false
        }

        holder.linearLayout.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()
            @Override
            fun onClick(view: View) {
               rowIndex = position
               notifyDataSetChanged()

                if(position==0){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Intro",R.drawable.csharpprogramming))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Strings",R.drawable.csharpprogramming))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Functions",R.drawable.csharpprogramming))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Graphics",R.drawable.csharpprogramming))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Networking",R.drawable.csharpprogramming))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("C-Sharp Programming, Project",R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)
                }
                else if (position==1){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Intro", R.drawable.dartlang))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, UI Dev", R.drawable.dartlang))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Maps", R.drawable.dartlang))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Firebase", R.drawable.dartlang))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Dart Mobile Development, Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==2){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Development, Intro", R.drawable.kotlin))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Mobile Development, UI Dev", R.drawable.kotlin))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Mobile Development, Messaging", R.drawable.kotlin))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Mobile Development, Maps", R.drawable.kotlin))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Mobile Development, Firebase", R.drawable.kotlin))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Flutter Mobile Development, Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==3){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript Development, Intro", R.drawable.javascript))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript Web Development, UI Dev", R.drawable.javascript))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript BackEnd Development, Messaging", R.drawable.javascript))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript Mobile Development, Maps", R.drawable.javascript))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript Frameworks, React", R.drawable.javascript))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("JavaScript Frameworks, Vue.js", R.drawable.javascript))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==4){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML Intro", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML UI Design", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML for Mobile", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML for Wearables", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Working with CSS", R.drawable.css))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==5){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML Intro", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML UI Design", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML for Mobile", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("HTML for Wearables", R.drawable.html))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Working with CSS", R.drawable.css))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==6){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Java Intro", R.drawable.java))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Java for Desktop", R.drawable.java))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Java for Mobile", R.drawable.java))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Java for Web", R.drawable.java))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Java for Robotics", R.drawable.java))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==7){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Git Intro", R.drawable.git))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Git v/s SVN", R.drawable.git))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Git and Github", R.drawable.git))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Git Recovery", R.drawable.git))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Git Merge v/s Rebase", R.drawable.git))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
                else if (position==8){
                    val dynamicRVItemsList:ArrayList<DynamicRecyclerViewModel> = ArrayList<DynamicRecyclerViewModel>()
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Databases Intro", R.drawable.databases))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Databases Design", R.drawable.databases))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("ERDs and DFD's", R.drawable.databases))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("NoSQL Databases", R.drawable.databases))
                    dynamicRVItemsList.add(DynamicRecyclerViewModel("Project", R.drawable.gradcap))

                    updateRecyclerView.callback(position,dynamicRVItemsList)

                }
            }
            if (select){
                if (position==0)
                    holder.linearLayout.setBackgroundResource(R.drawable.staticrecyclerviewselected_bg)
                select = false
            } else{
                if (rowIndex == position){
                    holder.linearLayout.setBackgroundResource(R.drawable.staticrecyclerviewselected_bg)
                }else{
                    holder.linearLayout.setBackgroundResource(R.drawable.staticrecyclerview_bg)
                }
            }

        }

    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}