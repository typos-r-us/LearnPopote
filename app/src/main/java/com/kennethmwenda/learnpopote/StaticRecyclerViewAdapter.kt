package com.kennethmwenda.learnpopote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.kennethmwenda.learnpopote.StaticRecyclerViewAdapter.StaticRecycleViewHolder

class StaticRecyclerViewAdapter(val listItems: ArrayList<StaticRecyclerViewModel>) : RecyclerView.Adapter<StaticRecycleViewHolder>() {
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

        holder.linearLayout.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()
//            @Override
//            fun onClick(view: View) {
//               rowIndex = position
//               notifyDataSetChanged()
//            }
            if (rowIndex == position){
                holder.linearLayout.setBackgroundResource(R.drawable.staticrecyclerviewselected_bg)
            }else{
                holder.linearLayout.setBackgroundResource(R.drawable.staticrecyclerview_bg)
            }
        }

    }

    override fun getItemCount(): Int {
       // TODO("Not yet implemented")
        return listItems.size
    }
}